package com.valentin.requests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RequestsManagementApplicationTests {

	@ServiceConnection
	static MySQLContainer mysql = new MySQLContainer("mysql:8.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mysql.start();
	}

	@Test
	void shouldCreateRequest() {
		String requestBody = """
				{
				    "requestType": "vacaciones",
				    "startingDate": "05/16/2026",
				    "finalDate": "05/30/2026",
				    "voucherRequired": false
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/requests")
				.then()
				.statusCode(201)
				.body("Id", notNullValue())
				.body("requestType", equalTo("vacaciones"))
				.body("requestVoucher", equalTo("NOT REQUIRED"));
	}


}
