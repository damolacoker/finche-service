package com.finche.db.repository;

import com.finche.db.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    CompletableFuture<Page<User>> findAllBy(final Pageable pageable);

    CompletableFuture<User> findOneById(final String id);

    CompletableFuture<List<User>> findAllBy();

    CompletableFuture<User> findOneByUsername(final String username);
}
