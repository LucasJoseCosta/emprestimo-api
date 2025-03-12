package com.emprestimo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.dto.CotacaoMoedaDTO;
import com.emprestimo.dto.MoedaDTO;
import com.emprestimo.service.BancoCentralServiceImp;

@RestController
@RequestMapping("/bcb")
public class BancoCentralController {
	
	@Autowired
	private BancoCentralServiceImp bancoCentralService;
	
	@GetMapping("moedas")
	public ResponseEntity<List<MoedaDTO>> listarMoedas() {
		List<MoedaDTO> moedas = bancoCentralService.getMoedas();
		if(moedas == null || moedas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(moedas);
	}
	
	@GetMapping("/cotacao")
	public ResponseEntity<Object> getCotacao(
	        @RequestParam String moeda,
	        @RequestParam String data) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    try {
	    	LocalDate.parse(data, formatter);
	    } catch (DateTimeParseException ex) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
	        errorResponse.put("message", "Data inválida. O formato esperado é MM-DD-AAAA (ex: 03-07-2025).");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
	    
	    List<CotacaoMoedaDTO> cotacoes = bancoCentralService.getCotacaoMoeda(moeda, data);
	    if (cotacoes == null || cotacoes.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(cotacoes);
	}
}
