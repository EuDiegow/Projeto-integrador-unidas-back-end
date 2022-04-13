package org.unidas.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioLogin {

	private long id;

	private String nome;
	
	@Schema(example = "email@email.com")
	private String usuario;

	private String senha;
	
	private String descricao;
	
	
	private String foto;
	
	private String tipo;
	
	private String token;

	
	
	public UsuarioLogin(long id, String nome, String usuario, String senha,String descricao,String foto,String tipo, String token) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.descricao=descricao;
		this.foto=foto;
		this.tipo=tipo;
		this.token = token;
	}

	public UsuarioLogin() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}
