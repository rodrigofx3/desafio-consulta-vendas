package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	private final SaleRepository repository;

	public SaleService(SaleRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDTO> getReport(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable) {
		return repository.getReport(minDate, maxDate, name, pageable);
	}

	@Transactional(readOnly = true)
	public List<SaleSummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate) {
		return repository.getSummary(minDate, maxDate);
	}

}
