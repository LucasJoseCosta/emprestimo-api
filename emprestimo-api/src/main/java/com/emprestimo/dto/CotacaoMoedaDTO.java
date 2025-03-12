package com.emprestimo.dto;

import java.math.BigDecimal;

public class CotacaoMoedaDTO {
	private int paridadeCompra;
    private int paridadeVenda;
    private BigDecimal cotacaoCompra;
    private BigDecimal cotacaoVenda;
    private String dataHoraCotacao;
    private String tipoBoletim;

    public CotacaoMoedaDTO(int paridadeCompra, int paridadeVenda, BigDecimal cotacaoCompra, BigDecimal cotacaoVenda,
			String dataHoraCotacao, String tipoBoletim) {
		super();
		this.paridadeCompra = paridadeCompra;
		this.paridadeVenda = paridadeVenda;
		this.cotacaoCompra = cotacaoCompra;
		this.cotacaoVenda = cotacaoVenda;
		this.dataHoraCotacao = dataHoraCotacao;
		this.tipoBoletim = tipoBoletim;
	}

	public int getParidadeCompra() {
        return paridadeCompra;
    }
    
    public void setParidadeCompra(int paridadeCompra) {
        this.paridadeCompra = paridadeCompra;
    }
    
    public int getParidadeVenda() {
        return paridadeVenda;
    }
    
    public void setParidadeVenda(int paridadeVenda) {
        this.paridadeVenda = paridadeVenda;
    }
    
    public BigDecimal getCotacaoCompra() {
        return cotacaoCompra;
    }
    
    public void setCotacaoCompra(BigDecimal cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }
    
    public BigDecimal getCotacaoVenda() {
        return cotacaoVenda;
    }
    
    public void setCotacaoVenda(BigDecimal cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }
    
    public String getDataHoraCotacao() {
        return dataHoraCotacao;
    }
    
    public void setDataHoraCotacao(String dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }
    
    public String getTipoBoletim() {
        return tipoBoletim;
    }
    
    public void setTipoBoletim(String tipoBoletim) {
        this.tipoBoletim = tipoBoletim;
    }
}


