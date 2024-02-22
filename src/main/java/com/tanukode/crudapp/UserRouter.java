package com.tanukode.crudapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class UserRouter{
    @Bean
    RouterFunction<ServerResponse> route(UserHandler handler){
        return RouterFunctions.route()
                .GET("/user", accept(MediaType.APPLICATION_JSON), handler::getUsers)
                .GET("/user/{uid}", accept(MediaType.APPLICATION_JSON), handler::getUser)
                .POST("/user", accept(MediaType.APPLICATION_JSON), handler::createUser)
                .PUT("/user/{uid}", accept(MediaType.APPLICATION_JSON), handler::updateUser)
                .DELETE("/user/{uid}", handler::deleteUser)
                .build();

    }
}
