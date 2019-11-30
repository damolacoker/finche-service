package com.finche.db.service;

import com.finche.db.model.Property;
import com.finche.db.repository.PropertyRepository;
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
public class PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;


    public Optional<Property> create(Property property) {
        return Optional.ofNullable(propertyRepository.save(property));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Property>> findAll(Pageable pageable) {
        return propertyRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Property>> findOneById(String id) {
        return propertyRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Property>> findAll() {
        return propertyRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String propertyId) {
        propertyRepository.findOneById(propertyId)
                .thenAccept(property -> {
                    propertyRepository.delete(property);
                }).exceptionally(
                throwable -> {
                    log.error("Unable to delete property", throwable);
                    return null;
                });
    }
}
