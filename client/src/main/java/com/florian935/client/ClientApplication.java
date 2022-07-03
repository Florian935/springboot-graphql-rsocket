package com.florian935.client;

import com.florian935.client.client.GreetingClient;
import com.florian935.client.domain.GraphqlPayload;
import com.florian935.client.domain.Greeting;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retrosocket.EnableRSocketClients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@SpringBootApplication
@EnableRSocketClients
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    ApplicationRunner requestRsocketGraphqlQuery(GreetingClient greetingClient) {
        return event -> {
            final String graphqlQuery = """
                    query {
                    	greeting {
                    		message
                    	}
                    }
                    """;

			final Mono<Map<String, String>> query = buildQuery(graphqlQuery);
			final Mono<GraphqlPayload<Greeting>> reply = greetingClient.greeting(query);
            reply.subscribe(System.out::println);
        };
    }

    @Bean
    ApplicationRunner requestRsocketGraphqlSubscription(GreetingClient greetingClient) {
        return event -> {
            final String graphqlSubscription = """
                    	subscription {
                    		greetings {
                    			message
                    		}
                    	}
                    """;

            final Mono<Map<String, String>> query = buildQuery(graphqlSubscription);
            final Flux<GraphqlPayload<Greeting>> reply = greetingClient.greetings(query);
            reply.subscribe(System.out::println);
        };
    }

    @Bean
    ApplicationRunner requestRsocketGraphqlMutation(GreetingClient greetingClient) {
        return event -> {
            final String message = "Hello from mutation response !";
            final String graphqlMutation = """
                    	mutation {
                    		addGreeting(message: "%s") {
                    			message
                    		}
                    	}
                    """.formatted(message);

            final Mono<Map<String, String>> query = buildQuery(graphqlMutation);
            final Mono<GraphqlPayload<Greeting>> reply = greetingClient.addGreeting(query);
            reply.subscribe(System.out::println);
        };
    }

    @Bean
    ApplicationRunner requestRsocketGraphqlSubscriptionWithArgument(GreetingClient greetingClient) {
        return event -> {
            final String message = "Salut !";
            final String graphqlSubscription = """
                    	subscription {
                    		greetingByMessage(message: "%s") {
                    			message
                    		}
                    	}
                    """.formatted(message);

            final Mono<Map<String, String>> query = buildQuery(graphqlSubscription);
            final Flux<GraphqlPayload<Greeting>> reply = greetingClient.greetingByMessage(query);
            reply.subscribe(System.out::println);
        };
    }

    private Mono<Map<String, String>> buildQuery(String graphqlQuery) {
        return Mono.just(
                Map.of("query", graphqlQuery)
        );
    }
}
