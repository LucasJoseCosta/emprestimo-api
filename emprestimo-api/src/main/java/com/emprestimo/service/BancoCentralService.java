package com.emprestimo.service;

import java.util.List;

import com.emprestimo.dto.CotacaoMoedaDTO;
import com.emprestimo.dto.MoedaDTO;

public interface BancoCentralService {
	public List<MoedaDTO> getMoedas();
	public List<CotacaoMoedaDTO> getCotacaoMoeda(String moeda, String dataCotacao);
}
