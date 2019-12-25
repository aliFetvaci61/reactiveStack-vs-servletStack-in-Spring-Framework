package com.alifetvaci.reactivestack.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.alifetvaci.reactivestack.domain.Library;

import reactor.core.publisher.Flux;

public interface LibraryMongoReactiveRepository extends ReactiveCrudRepository<Library, String>{
	
	@Query("{ id: { $exists: true }}")
    Flux<Library> retrieveAllLibraryPaged(final Pageable page);

}
