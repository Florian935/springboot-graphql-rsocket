package com.florian935.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

import java.net.URI;

@Configuration
public class RSocketConfiguration {

    @Bean
    @GreetingRequesterRSocket
    RSocketRequester greetingRequesterRSocket(RSocketStrategies rSocketStrategies) {
            final URI uri = URI.create("ws://localhost:7000/graphql");

            return RSocketRequester.builder()
                    .rsocketStrategies(rSocketStrategies)
                    .websocket(uri);

//        return builder.tcp("localhost", 7000);
    }
}
