package com.emprestimo.dto;

import java.util.List;

public class UsuarioPaginated {
	private List<UsuarioDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;

	public UsuarioPaginated() {}
	
	public UsuarioPaginated(List<UsuarioDTO> content, int pageNumber, int pageSize, long totalElements,
			int totalPages) {
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public List<UsuarioDTO> getContent() {
		return content;
	}

	public void setContent(List<UsuarioDTO> content) {
		this.content = content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
