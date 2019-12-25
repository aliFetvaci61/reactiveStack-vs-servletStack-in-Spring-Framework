package com.alifetvaci.servletstack.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alifetvaci.servletstack.domain.Library;
import com.alifetvaci.servletstack.repository.LibraryMongoBlockingRepository;




@RestController
public class LibraryBlockingController {

	private static final int DELAY_PER_ITEM_MS = 100;

	private LibraryMongoBlockingRepository libraryMongoBlockingRepository;

	public LibraryBlockingController(final LibraryMongoBlockingRepository libraryMongoBlockingRepository) {
		this.libraryMongoBlockingRepository = libraryMongoBlockingRepository;
	}

	@GetMapping("/library-blocking")
	public Iterable<Library> getLibrarysBlocking() throws Exception {
		Thread.sleep(DELAY_PER_ITEM_MS * libraryMongoBlockingRepository.count());
		return libraryMongoBlockingRepository.findAll();
	}

	@GetMapping("/library-blocking-paged")
	public Iterable<Library> getLibrarysBlocking(final @RequestParam(name = "page") int page,
			final @RequestParam(name = "size") int size) throws Exception {
		Thread.sleep(DELAY_PER_ITEM_MS * size);
		return libraryMongoBlockingRepository.retrieveAllLibraryPaged(PageRequest.of(page, size));
	}

}
