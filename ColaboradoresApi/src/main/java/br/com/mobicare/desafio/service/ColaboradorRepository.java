package br.com.mobicare.desafio.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mobicare.desafio.dto.ColaboradorDTO;
import br.com.mobicare.desafio.model.Colaborador;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

	// group by s.descricao, c.nome, c.email order by s.descricao asc
	
	@Query(value = " select new br.com.mobicare.desafio.dto.ColaboradorDTO "
			+ "(colaborador.id, colaborador.nome, colaborador.email, colaborador.dataNascimento, setor.descricao) "
			+ "from Colaborador colaborador inner join colaborador.setor setor where 1=1")
	List<ColaboradorDTO> listar();

	List<Colaborador> findByNomeIgnoreCaseContaining(String nome);
}
