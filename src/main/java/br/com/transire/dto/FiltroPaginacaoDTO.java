package br.com.transire.dto;

import java.io.Serializable;

public class FiltroPaginacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page;
	
	private Integer size;
	
	private String nome;
	
	private Integer id;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		System.out.println("size="+size);
		if (size == 0)
			return 10;
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String toString() {
		return " page=" + page + "\n size=" + size + "\n nome=" + nome + "\n id=" + id;
	}

}