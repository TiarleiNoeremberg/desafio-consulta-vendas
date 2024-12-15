package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleSellerDTO> getReport(String dataMinima, String dataMaxima, String name, Pageable pageable){
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());	//comando usado para instanciar a data atual na variavel "today".
		
		LocalDate maxima = dataMaxima.equals("") ? today : LocalDate.parse(dataMaxima);
		/* Codigo ternario "LocalDate maxima = dataMaxima.equals("") ? today : LocalDate.parse(dataMaxima);"
		 * (expressão booleana) ? código 1 : código 2
		 * Quando a expressão booleana for verdadeira, o código 1 é executado, e quando for falsa, o código 2 é executado
		 */
		LocalDate minima = dataMinima.equals("") ? maxima.minusYears(1L) : LocalDate.parse(dataMinima);
		
		Page<SaleSellerDTO> result = repository.getReport(minima, maxima, name, pageable);
		
		return result;
		
	}

	public List<SellerAmountDTO> getSummary(String dataMinima, String dataMaxima){
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		LocalDate maxima = dataMaxima.equals("") ? today : LocalDate.parse(dataMaxima);
		LocalDate minima = dataMinima.equals("") ? maxima.minusYears(1L) : LocalDate.parse(dataMinima);
		
		List<SellerAmountDTO> result = repository.getSummary(minima, maxima);
		
		return result;
	}
	
	
}
