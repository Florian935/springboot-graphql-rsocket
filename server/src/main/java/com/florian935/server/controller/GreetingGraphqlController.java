package com.florian935.server.controller;

import com.florian935.server.domain.Greeting;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GreetingGraphqlController {

    @QueryMapping
    Mono<Greeting> greeting() {
        return Mono.just(new Greeting("Hello World"));
    }
}
