package com.ens.pedidosAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ens.pedidosAPI.services", "com.ens.pedidosAPI.controllers"})
@EnableJpaRepositories(basePackages = "com.ens.pedidosAPI.repositories")
@EntityScan(basePackages = "com.ens.pedidosAPI.entities")
public class PedidosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosApiApplication.class, args); 
	}

}
