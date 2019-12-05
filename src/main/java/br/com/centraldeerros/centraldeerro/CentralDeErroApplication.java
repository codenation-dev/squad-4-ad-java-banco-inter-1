package br.com.centraldeerros.centraldeerro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.centraldeerros.centraldeerro"})
public class CentralDeErroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralDeErroApplication.class, args);
	}

}
