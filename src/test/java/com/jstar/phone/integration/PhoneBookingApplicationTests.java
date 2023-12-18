package com.jstar.phone.integration;

import com.jstar.phone.dto.BookPhoneRequest;
import com.jstar.phone.dto.ReturnPhoneRequest;
import com.jstar.phone.entities.Phone;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PhoneBookingApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:latest");

    @BeforeAll
    static void setup() {
        rabbitMQContainer.start();

        System.setProperty("spring.rabbitmq.host", rabbitMQContainer.getHost());
        System.setProperty("spring.rabbitmq.port", rabbitMQContainer.getAmqpPort().toString());
    }

    @AfterAll
    static void teardown() {
        rabbitMQContainer.stop();
    }

    @Test
    void testBookPhone() {
        var baseUrl = "http://localhost:" + port + "/api/phone/book";
        var request = new BookPhoneRequest("Oneplus 9", "User1");

        var authRestTemplate = restTemplate.withBasicAuth("admin", "admin");

        var response = authRestTemplate.postForEntity(baseUrl, request, Phone.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Oneplus 9", response.getBody().getModel());
        assertEquals("User1", response.getBody().getBookedBy());
    }

    @Test
    void testReturnPhone() {
        var baseUrl = "http://localhost:" + port + "/api/phone/return";
        var request = new ReturnPhoneRequest("Oneplus 9");

        var authRestTemplate = restTemplate.withBasicAuth("admin", "admin");

        var response = authRestTemplate.postForEntity(baseUrl, request, Phone.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Oneplus 9", response.getBody().getModel());
        assertNull(response.getBody().getBookedAt());
        assertNull(response.getBody().getBookedBy());
    }
}