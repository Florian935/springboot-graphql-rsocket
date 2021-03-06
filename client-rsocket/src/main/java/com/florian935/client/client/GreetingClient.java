package com.florian935.client.client;

import com.florian935.client.configuration.GreetingRequesterRSocket;
import com.florian935.client.domain.GraphqlPayload;
import com.florian935.client.domain.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retrosocket.RSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RSocketClient
@GreetingRequesterRSocket
public interface GreetingClient {

    @MessageMapping("graphql")
    Mono<GraphqlPayload<Greeting>> greeting(@Payload Mono<Map<String, String>> graphQLQuery);

    @MessageMapping("graphql")
    Flux<GraphqlPayload<Greeting>> greetings(@Payload Mono<Map<String, String>> graphQLQuery);

    @MessageMapping("graphql")
    Mono<GraphqlPayload<Greeting>> addGreeting(@Payload Mono<Map<String, String>> graphQLQuery);

    @MessageMapping("graphql")
    Flux<GraphqlPayload<Greeting>> greetingByMessage(@Payload Mono<Map<String, String>> graphqlQuery);
}
