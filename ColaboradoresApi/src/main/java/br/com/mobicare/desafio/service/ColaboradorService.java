package br.com.mobicare.desafio.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mobicare.desafio.dto.ColaboradorDTO;
import br.com.mobicare.desafio.model.Colaborador;
import br.com.mobicare.desafio.model.Setor;
import br.com.mobicare.desafio.repository.ColaboradorRepository;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@Service
public class ColaboradorService implements IColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Override
	public ColaboradorDTO buscarPorId(Long id) {
		return ColaboradorDTO.converter(colaboradorRepository.findById(id));
	}

	@Override
	public void remover(Long id) {
		colaboradorRepository.deleteById(id);
	}

	@Override
	@Transactional
	public ColaboradorDTO inserir(Colaborador colaborador) {

		// Gera um número aleatório entre 1 e 3 que representa o id do Setor.
		Long randomValue = Long.valueOf(new Random().ints(1, 1, 4).findFirst().getAsInt());
		colaborador.setSetor(new Setor(randomValue));

		return ColaboradorDTO.converter(Optional.of(colaboradorRepository.save(colaborador)));
	}

	@Override
	public List<ColaboradorDTO> listar() {
		return colaboradorRepository.listar();
	}

	@Override
	public List<ColaboradorDTO> buscarPorNome(String nome) {
		return colaboradorRepository.findByNomeIgnoreCaseContaining(nome).stream()
				.map(colaborador -> ColaboradorDTO.converter(Optional.of(colaborador)))
				.collect(Collectors.toList());
	}
}
