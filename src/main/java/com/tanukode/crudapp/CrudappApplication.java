package com.tanukode.crudapp;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryMetadata;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

@EnableWebFlux
@SpringBootApplication
public class CrudappApplication {
    private static final Logger log = LoggerFactory.getLogger(CrudappApplication.class);
    
    public static void main(String[] args) {

        SpringApplication.run(CrudappApplication.class, args);

    }



    @Bean
    public CommandLineRunner commandLineRunner(UserRepository repository){
        return (args)->{
            repository.findAll().doOnNext(user->{
                log.info(user.toString());
            }).blockLast(Duration.ofSeconds(10));
        };
    }
}
