package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public void invokeService() {
        String serviceUrl = "http://localhost:8090";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(serviceUrl, String.class);

        System.out.println("Response from the service: " + response);
    }
}
