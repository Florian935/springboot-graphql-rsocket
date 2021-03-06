package com.florian935.client;

import com.florian935.client.domain.Greeting;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
	ApplicationRunner httpGraphQlClientQuery(HttpGraphQlClient httpGraphQlClient) {
		return event -> {
			final String httpRequestDocument = """
						query {
							greeting {
								message
							}
						}
					""";
				final Mono<Greeting> response = httpGraphQlClient.document(httpRequestDocument)
							.retrieve("greeting")
							.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("HTTP QUERY ## " + greeting));

		};
	}

	@Bean
	ApplicationRunner httpGraphQlClientQueryWithPayload(HttpGraphQlClient httpGraphQlClient) {
		return event -> {
			final String httpRequestDocument = """
						query greetingMessage($message: String) {
							greetingMessage(message: $message) {
								message
							}
						}
					""";
			final Mono<Greeting> response = httpGraphQlClient.document(httpRequestDocument)
					.variable("message", "Hello Http Query")
					.retrieve("greetingMessage")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("HTTP QUERY WITH PAYLOAD ## " + greeting));

		};
	}

	@Bean
	ApplicationRunner httpGraphQlClientMutation(HttpGraphQlClient httpGraphQlClient) {
		return event -> {
			final String httpRequestDocument = """
						mutation addGreeting($message: String) {
							addGreeting(message: $message) {
								message
							}
						}
					""";

			final Mono<Greeting> response = httpGraphQlClient.document(httpRequestDocument)
					.variable("message", "Hello Http Mutation")
					.retrieve("addGreeting")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("HTTP MUTATION ## " + greeting));

		};
	}

	@Bean
	ApplicationRunner rSocketGraphQlClientQuery(RSocketGraphQlClient rSocketGraphQlClient) {
		return event -> {
			final String rSocketRequestDocument = """
						query {
							greeting {
								message
							}
						}
					""";
			final Mono<Greeting> response = rSocketGraphQlClient.document(rSocketRequestDocument)
					.retrieve("greeting")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("RSOCKET QUERY ## " + greeting));
		};
	}

	@Bean
	ApplicationRunner rSocketGraphQlClientQueryWithPayload(RSocketGraphQlClient rSocketGraphQlClient) {
		return event -> {
			final String rSocketRequestDocument = """
						query greetingMessage($message: String) {
							greetingMessage(message: $message) {
								message
							}
						}
					""";
			final Mono<Greeting> response = rSocketGraphQlClient.document(rSocketRequestDocument)
					.variable("message", "Hello RSocket Query")
					.retrieve("greetingMessage")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("RSOCKET QUERY WITH PAYLOAD ## " + greeting));
		};
	}

	@Bean
	ApplicationRunner rSocketGraphQlClientMutation(RSocketGraphQlClient rSocketGraphQlClient) {
		return event -> {
			final String rSocketRequestDocument = """
						mutation addGreeting($message: String) {
							addGreeting(message: $message) {
								message
							}
						}
					""";

			Mono<Greeting> response = rSocketGraphQlClient.document(rSocketRequestDocument)
					.variable("message", "Hello RSocket Mutation")
					.retrieve("addGreeting")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("RSOCKET MUTATION ## " + greeting));
		};
	}

	@Bean
	ApplicationRunner rSocketGraphQlClientSubscription(RSocketGraphQlClient rSocketGraphQlClient) {
		return event -> {
			final String httpRequestDocument = """
						subscription {
							greetings {
								message
							}
						}
					""";

			final Flux<Greeting> response = rSocketGraphQlClient.document(httpRequestDocument)
					.retrieveSubscription("greetings")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("RSOCKET SUBSCRIPTION ## " + greeting));
		};
	}

	@Bean
	ApplicationRunner rSocketGraphQlClientSubscriptionWithPayload(RSocketGraphQlClient rSocketGraphQlClient) {
		return event -> {
			final String httpRequestDocument = """
						subscription greetingByMessage($message: String) {
							greetingByMessage(message: $message) {
								message
							}
						}
					""";

			final Flux<Greeting> response = rSocketGraphQlClient.document(httpRequestDocument)
					.variable("message", "Hello RSocket Subscription")
					.retrieveSubscription("greetingByMessage")
					.toEntity(Greeting.class);

			response.subscribe(greeting -> System.out.println("RSOCKET SUBSCRIPTION WITH PAYLOAD ## " + greeting));
		};
	}
}
