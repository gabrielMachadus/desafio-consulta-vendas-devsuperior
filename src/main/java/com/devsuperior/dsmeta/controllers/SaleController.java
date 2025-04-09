package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/report")
	public Page<SaleReportDTO> report(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate,
			@RequestParam(required = false) String name,
			Pageable pageable) {

		return service.getReport(minDate, maxDate, name, pageable);
	}

	@GetMapping("/summary")
	public List<SaleSummaryDTO> summary(
			@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate) {

		return service.getSummary(minDate, maxDate);
	}
}
