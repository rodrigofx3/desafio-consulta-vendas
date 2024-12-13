package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	private final SaleService service;

	public SaleController(SaleService service) {
		this.service = service;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
		@RequestParam(value = "name", defaultValue = "") String name,
		@RequestParam(value = "minDate", defaultValue = "") LocalDate minDate,
		@RequestParam(value = "maxDate", defaultValue = "") LocalDate maxDate,
		Pageable pageable) {
		LocalDate today = LocalDate.now();
		LocalDate oneYearAgo = today.minusYears(1L);
		if (maxDate == null) {
			maxDate = today;
		}
		if (minDate == null) {
			minDate = oneYearAgo;
		}
		Page<SaleReportDTO> list = service.getReport(name, minDate, maxDate, pageable);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryDTO>> getSummary(
			@RequestParam(value = "minDate", defaultValue = "") LocalDate minDate,
			@RequestParam(value = "maxDate", defaultValue = "") LocalDate maxDate
	) {
		LocalDate today = LocalDate.now();
		LocalDate oneYearAgo = today.minusYears(1L);
		if (maxDate == null) {
			maxDate = today;
		}
		if (minDate == null) {
			minDate = oneYearAgo;
		}
		List<SaleSummaryDTO> list = service.getSummary(minDate, maxDate);
		return ResponseEntity.ok(list);
	}
}
