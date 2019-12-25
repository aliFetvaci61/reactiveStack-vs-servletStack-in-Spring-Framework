package com.alifetvaci.servletstack.repository;


import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alifetvaci.servletstack.domain.Library;



public interface LibraryMongoBlockingRepository extends CrudRepository<Library, String>{
	
	 @Query("{ id: { $exists: true }}")
	 List<Library> retrieveAllLibraryPaged(final PageRequest pageRequest);

}
