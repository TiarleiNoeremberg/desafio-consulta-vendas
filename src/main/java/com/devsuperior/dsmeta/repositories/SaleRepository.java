package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :dataMinima AND :dataMaxima "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleSellerDTO> getReport(LocalDate dataMinima, LocalDate dataMaxima, String name, Pageable pageable);


    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerAmountDTO(obj.seller.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :dataMinima AND :dataMaxima "
            + "GROUP BY obj.seller.name" )
    List<SellerAmountDTO> getSummary(LocalDate dataMinima, LocalDate dataMaxima);
}
