package br.com.transire.dto;

/**
 * 
 * @author claudemir
 */

public class VendaProdutoDTO {

	private Integer id;

	private ProdutoDTO produto;

	private VendaDTO venda;

	private double valor;

	private Integer quantidade;
	
	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

	public VendaDTO getVenda() {
		return venda;
	}

	public void setVenda(VendaDTO venda) {
		this.venda = venda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public VendaProdutoDTO() {
	}

	public VendaProdutoDTO(Integer id, String nome, double valor) {
		this.id = id;
		this.valor = valor;
	}

	public VendaProdutoDTO(Integer id) {
		this.id = id;
	}

}