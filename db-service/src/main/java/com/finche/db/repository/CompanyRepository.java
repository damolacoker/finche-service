package com.finche.db.repository;

import com.finche.db.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    CompletableFuture<Page<Company>> findAllBy(final Pageable pageable);

    CompletableFuture<Company> findOneById(final String id);

    CompletableFuture<List<Company>> findAllBy();
}
