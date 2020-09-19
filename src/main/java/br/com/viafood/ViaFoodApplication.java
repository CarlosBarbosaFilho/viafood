package br.com.viafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.viafood.utils.infrastrutura.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ViaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViaFoodApplication.class, args);
	}

}
