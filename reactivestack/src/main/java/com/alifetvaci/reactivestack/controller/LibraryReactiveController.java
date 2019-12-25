package com.alifetvaci.reactivestack.controller;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.alifetvaci.reactivestack.domain.Library;
import com.alifetvaci.reactivestack.repository.LibraryMongoReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class LibraryReactiveController {

	private static final int DELAY_PER_ITEM_MS = 100;

	private final WebClient webClient;

	private LibraryMongoReactiveRepository libraryMongoReactiveRepository;

	public LibraryReactiveController(final LibraryMongoReactiveRepository libraryMongoReactiveRepository,
			@Value("${user.service.host}") String userServiceHost) {
		this.libraryMongoReactiveRepository = libraryMongoReactiveRepository;
		this.webClient = WebClient.builder().baseUrl(userServiceHost).build();
	}

	@GetMapping("/library-reactive")
	public Flux<Library> getLibraryFlux() {
		// If you want to use a shorter version of the Flux, use take(100) at the end of
		// the statement below
		return libraryMongoReactiveRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@GetMapping("/library-reactive-paged")
	public Flux<Library> getLibraryFlux(final @RequestParam(name = "page") int page,
			final @RequestParam(name = "size") int size) {
		return libraryMongoReactiveRepository.retrieveAllLibraryPaged(PageRequest.of(page, size))
				.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@GetMapping(value = "/string-reactive", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<String> getStringFlux() {
		return Flux.interval(Duration.ofSeconds(1)).map(aLong -> "Hello - " + Instant.now() + "\n");
	}

	@GetMapping(value = "/webflux-webclient")
	public Mono<String> getUserUsingWebfluxWebclient(@RequestParam long delay) {
		return webClient.get().uri("/user/?delay={delay}", delay).retrieve().bodyToMono(String.class)
				.map(x -> "webflux-webclient: " + x);
	}

}
