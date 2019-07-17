package br.com.mobicare.desafio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@Entity
@Table(name = "SETOR")
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SETR_ID")
	private Long id;

	@Column(name = "SETR_DESCRICAO")
	private String descricao;

	@JsonIgnore
	@OneToMany(mappedBy = "setor", targetEntity = Colaborador.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Colaborador> colaboradores;
	
	public Setor() {}

	public Setor(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
}