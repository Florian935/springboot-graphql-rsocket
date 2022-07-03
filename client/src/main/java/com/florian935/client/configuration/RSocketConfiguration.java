package com.florian935.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketConfiguration {

    @Bean
    @GreetingRequesterRSocket
    RSocketRequester greetingRequesterRSocket(RSocketRequester.Builder builder) {
        return builder.tcp("localhost", 7000);
    }
}
