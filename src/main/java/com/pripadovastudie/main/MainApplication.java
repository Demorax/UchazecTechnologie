package com.pripadovastudie.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





/*
* Databázi k ukládání používám naši školní databázi Oracle
* K ověření dat, sekvencí používám program SQLDeveloper od firmy Oracle
* K vkládání, ověřování, mazání, ... používám Postman
* */
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
