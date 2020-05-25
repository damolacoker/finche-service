package com.finche.db.service;

import com.finche.db.model.Bid;
import com.finche.db.repository.BidRepository;
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
public class BidService {

    @Autowired
    private BidRepository bidRepository;


    public Optional<Bid> create(Bid bid) {

        return Optional.ofNullable(bidRepository.save(bid));
    }

    public Optional<Bid> save(Bid bid) {
        return Optional.empty();
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Bid>> findAll(Pageable pageable) {
        return bidRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Bid>> findOneById(String id) {
        return bidRepository
                .findOneById(id)
                .thenApply(Optional::ofNullable);
    }

    public CompletableFuture<Optional<Bid>> findOneByName(String projectName) {
        return bidRepository
                .findOneByName(projectName)
                .thenApplyAsync(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Bid>> findAll() {
        return bidRepository.findAllBy();
    }

    public void delete(String bidId) {
        bidRepository.findOneById(bidId)
                .thenAccept(bid -> bidRepository.delete(bid))
                .exceptionally(throwable -> {
                    log.error("Unable to delete bid", throwable);
                    return null;
                });
    }
}
