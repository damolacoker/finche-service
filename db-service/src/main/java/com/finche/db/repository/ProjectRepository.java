package com.finche.db.repository;

import com.finche.db.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    CompletableFuture<Page<Project>> findAllBy(final Pageable pageable);

    CompletableFuture<Project> findOneById(final String id);

    CompletableFuture<List<Project>> findAllBy();
}
