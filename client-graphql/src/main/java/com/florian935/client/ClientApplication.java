package com.florian935.client;

import com.florian935.client.domain.Greeting;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;
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

			response.subscribe(greeting -> System.out.println("HTTP ## " + greeting));

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

			response.subscribe(greeting -> System.out.println("RSOCKET ## " + greeting));
		};
	}
}
