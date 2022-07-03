package com.florian935.server.controller;

import com.florian935.server.domain.Greeting;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@Controller
public class GreetingGraphqlController {

    @QueryMapping
    Mono<Greeting> greeting() {
        return Mono.just(new Greeting("Hello World"));
    }

    @SubscriptionMapping
    Flux<Greeting> greetings() {
            return Flux.fromStream(Stream.generate(() -> new Greeting("Hello, world @ " + Instant.now())))
                    .delayElements(Duration.ofSeconds(1))
                    .take(10);
    }
}
