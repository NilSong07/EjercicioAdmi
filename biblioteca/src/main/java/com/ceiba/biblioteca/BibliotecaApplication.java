package com.ceiba.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ceiba.biblioteca.config")
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
