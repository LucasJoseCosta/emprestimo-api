package com.emprestimo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoResponseDTO {
    @JsonProperty("@odata.context")
    private String odataContext;
    private List<CotacaoMoedaDTO> value;
    
    public CotacaoResponseDTO(String odataContext, List<CotacaoMoedaDTO> value) {
		super();
		this.odataContext = odataContext;
		this.value = value;
	}
    
    public String getOdataContext() {
        return odataContext;
    }
    
    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }
    
    public List<CotacaoMoedaDTO> getValue() {
        return value;
    }
    
    public void setValue(List<CotacaoMoedaDTO> value) {
        this.value = value;
    }
}
