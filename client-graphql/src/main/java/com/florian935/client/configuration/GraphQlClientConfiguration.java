package com.florian935.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;

@Configuration
public class GraphQlClientConfiguration {

    @Bean
    HttpGraphQlClient httpGraphQlClient() {
        return HttpGraphQlClient.builder()
                .url("http://localhost:8080/graphql")
                .build();
    }

    @Bean
    RSocketGraphQlClient rSocketGraphQlClient() {
        return RSocketGraphQlClient.builder()
                .tcp("localhost", 7000)
                .route("graphql")
                .build();
    }
}
