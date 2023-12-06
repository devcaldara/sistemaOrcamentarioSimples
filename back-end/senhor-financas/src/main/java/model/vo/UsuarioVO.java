package model.vo;

import java.time.LocalDate;

public class UsuarioVO {
	
	private int idUsuario;
	private String nome;
	private String cpf;
	private String email;
	private LocalDate dataNascimento;
	private String login;
	private String senha;
	
	public UsuarioVO(int iD, String nome, String cpf, String email, LocalDate dataNascimento, String login,
			String senha) {
		super();
		this.idUsuario = iD;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.senha = senha;
	}

	public UsuarioVO() {
		super();
	}
	
	public int getidUsuario() {
		return idUsuario;
	}

	public void setidUsuario(int iD) {
		this.idUsuario = iD;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}