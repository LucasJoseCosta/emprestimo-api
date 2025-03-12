package com.emprestimo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoedaResponseDTO {
	@JsonProperty("@odata.context")
    private String odataContext;
    private List<MoedaDTO> value;
    
    public MoedaResponseDTO(String odataContext, List<MoedaDTO> value) {
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
    
    public List<MoedaDTO> getValue() {
        return value;
    }
    
    public void setValue(List<MoedaDTO> value) {
        this.value = value;
    }
    
}
