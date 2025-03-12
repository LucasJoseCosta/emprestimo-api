package com.emprestimo.dto;

public class MoedaDTO {
	private String simbolo;
    private String nomeFormatado;
    private String tipoMoeda;

    public MoedaDTO(String simbolo, String nomeFormatado, String tipoMoeda) {
		super();
		this.simbolo = simbolo;
		this.nomeFormatado = nomeFormatado;
		this.tipoMoeda = tipoMoeda;
	}
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public String getNomeFormatado() {
        return nomeFormatado;
    }
    
    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }
    
    public String getTipoMoeda() {
        return tipoMoeda;
    }
    
    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }
}
