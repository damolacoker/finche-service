package com.finche.db.repository;

import com.finche.db.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface PropertyRepository extends MongoRepository<Property, String> {

    CompletableFuture<Page<Property>> findAllBy(final Pageable pageable);

    CompletableFuture<Property> findOneById(final String id);

    CompletableFuture<Property> findByCountry(final String country);

    CompletableFuture<List<Property>> findAllBy();
}
