package ru.sberbank.transactionmanager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionmanagerApplication.class, args);
	}

}
