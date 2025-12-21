package com.valentin.requests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.mysql.MySQLContainer;

import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RequestsManagementApplicationTests {

	@ServiceConnection
	static MySQLContainer mysql = new MySQLContainer("mysql:8.0");

	@LocalServerPort
	private Integer port;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@AfterEach
	void cleanup() {
		jdbcTemplate.execute("TRUNCATE TABLE request");
	}

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
				    "voucherRequired": false,
				    "employeeId": "12345"
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

	@Test
	void shouldGetOneRequest() {
		String requestBody = """
				{
				    "requestType": "vacaciones",
				    "startingDate": "05/16/2026",
				    "finalDate": "05/30/2026",
				    "voucherRequired": false,
				    "employeeId": "12345"
				}
				""";
		// Insert element
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/requests")
				.then()
				.statusCode(201);
		// Verify quantity
		RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/requests")
				.then()
				.statusCode(200)
				.body("$", hasSize(1));
	}

	@Test
	void shouldUpdateRequest() {
		String requestBody = """
				{
				    "requestType": "vacaciones",
				    "startingDate": "05/16/2026",
				    "finalDate": "05/30/2026",
				    "voucherRequired": false,
				    "employeeId": "12345"
				}
				""";
		String updatedBody = """
				{
				    "requestType": "incapacidad",
				    "startingDate": "06/01/2026",
				    "finalDate": "06/30/2026",
				    "voucherRequired": true,
				    "requestVoucher": "BASE64....",
				    "employeeId": "12345"
				}
				""";

		// Insert element
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/requests")
				.then()
				.statusCode(201);

		// Update and verify contents
		RestAssured.given()
				.contentType("application/json")
				.body(updatedBody)
				.param("id",1)
				.when()
				.put("/requests")
				.then()
				.statusCode(202)
				.body("Id", notNullValue())
				.body("requestType", equalTo("incapacidad"))
				.body("requestVoucher", equalTo("BASE64...."));
	}

}
