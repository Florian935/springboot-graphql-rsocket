package com.florian935.client.client;

import com.florian935.client.configuration.GreetingRequesterRSocket;
import com.florian935.client.domain.GraphqlPayload;
import com.florian935.client.domain.Greeting;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retrosocket.RSocketClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RSocketClient
@GreetingRequesterRSocket
public interface GreetingClient {

    @MessageMapping("graphql")
    Mono<GraphqlPayload<Greeting>> greeting(@Payload Mono<Map<String, String>> graphQLQuery);
}
