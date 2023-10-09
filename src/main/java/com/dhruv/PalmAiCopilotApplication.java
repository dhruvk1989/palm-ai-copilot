package com.dhruv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PalmAiCopilotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalmAiCopilotApplication.class, args);
    }

    @Bean(name = "appRestClient")
    public RestTemplate getRestClient() {

        RestTemplate  restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        return restClient;
    }

}
