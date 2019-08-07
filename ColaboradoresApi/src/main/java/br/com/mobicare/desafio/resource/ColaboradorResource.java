package br.com.mobicare.desafio.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mobicare.desafio.dto.ColaboradorDTO;
import br.com.mobicare.desafio.model.Colaborador;
import br.com.mobicare.desafio.service.IColaboradorService;

/**
 *
 * @author Leonardo de Souza Rocha
 *
 */
@RestController
@RequestMapping("/colaboradores")
// @CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class ColaboradorResource {

	@Autowired
	private IColaboradorService colaboradorService;

	/**
	 *
	 * Método responsável por buscar a criar um colaborador.
	 *
	 * @param colaborador
	 * @param response
	 * @return
	 *
	 */
	@PostMapping
	public ResponseEntity<ColaboradorDTO> criar(@Valid @RequestBody Colaborador colaborador,
			HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.colaboradorService.inserir(colaborador));
	}

	/**
	 *
	 * Método responsável por listar todos os colaboradores da base de dados.
	 *
	 * @return
	 *
	 */
	@GetMapping
	public List<ColaboradorDTO> listar() {
		return this.colaboradorService.listar();
	}

	/**
	 * Método responsável por buscar um colaborador por id.
	 *
	 * @param id
	 * @param response
	 * @return
	 *
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ColaboradorDTO> buscarPorId(@PathVariable final Long id, HttpServletResponse response) {

		ColaboradorDTO colaboradorBd = this.colaboradorService.buscarPorId(id);

		if (colaboradorBd == null) {
			return ResponseEntity.notFound().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();

		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(colaboradorBd);
	}

	/**
	 *
	 * Método responsável por filtrar os colaboradores por nome.
	 *
	 * @param nome
	 * @param response
	 * @return
	 *
	 */
	@GetMapping(path = "/buscarPorNome")
	public List<ColaboradorDTO> buscarPorNome(@RequestParam(required = false, defaultValue = "%") String nome, HttpServletResponse response) {
		return this.colaboradorService.buscarPorNome(nome);
	}

	/**
	 *
	 * Método responsável por deletar um colaborador.
	 *
	 * @param id
	 *
	 */
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable final Long id) {
		this.colaboradorService.remover(id);
	}
}
