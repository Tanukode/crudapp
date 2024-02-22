package com.tanukode.crudapp;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class UserClient {
    final WebClient webClient = WebClient.builder().baseUrl("Http://localhost:8080").build();
    public UserClient(){}

}
