package br.com.transire.dto;

import java.io.Serializable;
import java.sql.Date;

import br.com.transire.model.Cliente;

public class VendaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Date data;

	private double valor;

	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}