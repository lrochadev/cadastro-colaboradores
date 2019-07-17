package br.com.mobicare.desafio.dto;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import br.com.mobicare.desafio.model.Colaborador;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ColaboradorDTO {

	protected Long id;
	protected String cpf;
	protected String nome;
	protected String telefone;
	protected String email;
	protected LocalDate dataNascimento;
	protected String descricaoSetor;
	protected int idade;

	public ColaboradorDTO() {
	}

	public ColaboradorDTO(Long id, String nome, String email, LocalDate dataNascimento, String descricaoSetor) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.descricaoSetor = descricaoSetor;
		this.idade = calcularIdade(this.dataNascimento.getYear());
	}

	public static ColaboradorDTO converter(Optional<Colaborador> colaborador) {
		ColaboradorDTO dto = new ColaboradorDTO();

		if (!colaborador.isPresent())
			return null;

		dto.id = colaborador.get().getId();
		dto.cpf = colaborador.get().getCpf();
		dto.nome = colaborador.get().getNome();
		dto.telefone = colaborador.get().getTelefone();
		dto.email = colaborador.get().getEmail();
		dto.dataNascimento = colaborador.get().getDataNascimento();
		dto.descricaoSetor = colaborador.get().getSetor().getDescricao();
		dto.idade = calcularIdade(colaborador.get().getDataNascimento().getYear());

		return dto;
	}

	private static int calcularIdade(int anoNascimento) {
		return LocalDate.now().getYear() - anoNascimento;
	}
}