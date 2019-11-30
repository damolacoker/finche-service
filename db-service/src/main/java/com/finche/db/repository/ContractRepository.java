package com.finche.db.repository;

import com.finche.db.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {
    CompletableFuture<Page<Contract>> findAllBy(final Pageable pageable);
    CompletableFuture<Contract> findOneById(final String id);
    CompletableFuture<List<Contract>> findAllBy();
}
