package model.vo;

import java.time.LocalDate;

public class ReceitaVO {

	private int idReceita;
	private int idUsuario;
	private String descricao;
	private LocalDate data;
	private double valor;
	
	public ReceitaVO(int idReceita, int idUsuario, String descricao, LocalDate data, double valor) {
		super();
		this.idReceita = idReceita;
		this.idUsuario = idUsuario;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
	}

	public ReceitaVO() {
		super();
	}

	public int getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}	
}
