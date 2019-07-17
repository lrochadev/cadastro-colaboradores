package br.com.mobicare.desafio.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.mobicare.desafio.repository.ColaboradorRepository;

/**
 * 
 * @author Leonardo de Souza Rocha
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses= {ColaboradorRepository.class})
@ComponentScan(basePackages = "br.com.mobicare.desafio.*")
@EntityScan(basePackages = "br.com.mobicare.desafio.model")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
 
