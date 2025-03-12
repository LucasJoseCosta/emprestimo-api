package com.emprestimo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.emprestimo.dto.CotacaoMoedaDTO;
import com.emprestimo.dto.CotacaoResponseDTO;
import com.emprestimo.dto.MoedaDTO;
import com.emprestimo.dto.MoedaResponseDTO;

@Service
public class BancoCentralServiceImp implements BancoCentralService {

	private final RestTemplate restTemplate;

	public BancoCentralServiceImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<MoedaDTO> getMoedas() {
		String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/Moedas?$top=100&$format=json";
		MoedaResponseDTO response = restTemplate.getForObject(url, MoedaResponseDTO.class);
		return response != null ? response.getValue() : null;
	}

	@Override
	public List<CotacaoMoedaDTO> getCotacaoMoeda(String moeda, String dataCotacao) {
		String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?"
				+ "@moeda={moeda}&@dataCotacao={data}&$top=100&$format=json";

		Map<String, String> params = new HashMap<>();
		params.put("moeda", "'" + moeda + "'");
	    params.put("data", "'" + dataCotacao + "'");

		CotacaoResponseDTO response = restTemplate.getForObject(url, CotacaoResponseDTO.class, params);
		return response != null ? response.getValue() : null;
	}

}
