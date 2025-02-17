package com.example.spring;

import org.springframework.boot.SpringApplication;

public class TestRequestsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.from(RequestsManagementApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
