package com.florian935.client.configuration;

import io.rsocket.transport.netty.client.WebsocketClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;
import reactor.netty.tcp.TcpClient;

import java.net.URI;

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
        final URI uri = URI.create("ws://localhost:7000/graphql");

        return RSocketGraphQlClient.builder()
                .webSocket(uri)
                .build();

//        return RSocketGraphQlClient.builder()
//                .tcp("localhost", 7000)
//                .route("graphql")
//                .build();
    }
}
