package br.com.mobicare.desafio.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "COLABORADOR")
public class Colaborador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COLA_ID")
	private Long id;

	@NotNull
	@Column(name = "COLA_CPF")
	private String cpf;

	@NotNull
	@Column(name = "COLA_NOME")
	private String nome;

	@NotNull
	@Column(name = "COLA_TELEFONE")
	private String telefone;

	@NotNull
	@Column(name = "COLA_EMAIL")
	private String email;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "COLA_DT_NASCIMENTO")
	private LocalDate dataNascimento;

	@ManyToOne
	@JoinColumn(name = "SETR_ID")
	private Setor setor;
}