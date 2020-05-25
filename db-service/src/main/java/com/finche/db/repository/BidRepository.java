package com.finche.db.repository;

import com.finche.db.model.Bid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface BidRepository extends MongoRepository<Bid, String> {
    CompletableFuture<Page<Bid>> findAllBy(final Pageable pageable);

    CompletableFuture<Bid> findOneById(final String id);

    CompletableFuture<List<Bid>> findAllBy();

    CompletableFuture<Bid> findOneByName(final String projectName);

}
