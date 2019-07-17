package br.com.mobicare.desafio.service;

import java.util.List;

import br.com.mobicare.desafio.dto.ColaboradorDTO;
import br.com.mobicare.desafio.model.Colaborador;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
public interface IColaboradorService {

	ColaboradorDTO buscarPorId(Long id);
	
	List<ColaboradorDTO> buscarPorNome(String nome);

	void remover(Long id);

	ColaboradorDTO inserir(Colaborador colaborador);

	List<ColaboradorDTO> listar();

}
