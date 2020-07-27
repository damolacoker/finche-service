package com.finche.db.service;

import com.finche.db.model.Contract;
import com.finche.db.model.Documents;
import com.finche.db.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public Optional<Documents> create(Documents document) {
        return Optional.ofNullable(fileRepository.save(document));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Documents>> findAll(Pageable pageable) {
        return fileRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Documents>> findOneById(String id) {
        return fileRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Documents>> findOneByName(String name) {
        return fileRepository.findOneByName(name).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Documents>> findAll() {
        return fileRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String documentId) {
        fileRepository
                .findOneById(documentId)
                .thenAccept(document -> fileRepository.delete(document)).exceptionally(throwable -> {
            log.error("Unable to delete document", throwable);
            return null;
        });
    }

}
