package model.vo;

import java.time.LocalDate;

public class DespesaVO {

	private int idDespesa;
	private int idUsuario;
	private String descricao;
	private double valor;
	private LocalDate dataVencimento;
	private LocalDate dataPagamento;
	
	public DespesaVO(int idDespesa, int idUsuario, String descricao, double valor, LocalDate dataVencimento,
			LocalDate dataPagamento) {
		super();
		this.idDespesa = idDespesa;
		this.idUsuario = idUsuario;
		this.descricao = descricao;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public DespesaVO() {
		super();
	}

	public int getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	
}