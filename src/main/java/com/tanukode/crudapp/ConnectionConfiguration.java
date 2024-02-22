package com.tanukode.crudapp;

import io.r2dbc.spi.ConnectionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;


@Configuration
public class ConnectionConfiguration{
    @Bean
    ConnectionFactoryInitializer connectionFactoryInitializer(ConnectionFactory connectionFactory){
        ConnectionFactoryInitializer initializer =  new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Bean
    R2dbcMappingContext mappingContext(){
        R2dbcMappingContext map = new R2dbcMappingContext();
        map.setForceQuote(true);

        return map;
    }



}
