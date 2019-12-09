package br.com.centraldeerros.centraldeerro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(scanBasePackages = {"br.com.centraldeerros.centraldeerro"})
@EnableJpaRepositories(basePackages = {"br.com.centraldeerros.centraldeerro.repositories"})
//@EnableSpringDataWebSupport
public class CentralDeErroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralDeErroApplication.class, args);
	}

}
