package com.finche.db.repository;

import com.finche.db.model.Documents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface FileRepository extends MongoRepository<Documents,String> {
    CompletableFuture<Page<Documents>> findAllBy(final Pageable pageable);

    CompletableFuture<Documents> findOneById(final String id);

    CompletableFuture<Documents> findOneByName(final String name);

    CompletableFuture<List<Documents>> findAllBy();
}
