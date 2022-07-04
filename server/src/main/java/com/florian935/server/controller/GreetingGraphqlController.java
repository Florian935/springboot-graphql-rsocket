package com.florian935.server.controller;

import com.florian935.server.domain.Greeting;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
                    .take(5);
    }

    @MutationMapping
    Mono<Greeting> addGreeting(@Argument String message) {
        return Mono.just(new Greeting(message));
    }

    @SubscriptionMapping
    Flux<Greeting> greetingByMessage(@Argument String message) {
        return Flux.fromStream(Stream.generate(() -> new Greeting("Greeting from message ## " + message)))
                .delayElements(Duration.ofSeconds(1))
                .take(5);
    }

    @QueryMapping
    Mono<Greeting> greetingMessage(@Argument String message) {
        return Mono.just(new Greeting(message));
    }
}
