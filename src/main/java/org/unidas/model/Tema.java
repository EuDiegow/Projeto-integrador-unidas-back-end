package org.unidas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

@Table(name = "tb_temas")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


	@NotBlank(message = "O nome título é Obrigatório!")
	@Size(min = 1, max = 50,message="o nome deve conter entre 1 e 50 caracteres")
	private String nome;
	
	@NotBlank(message = "A descricao título é Obrigatório!")
	@Size(min = 1, max = 500,message="a descrição deve conter entre 1 e 500 caracteres")
	private String descricao;

	@OneToMany(mappedBy = "tema",cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;
	
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}
