package com.finche.db.service;

import com.finche.db.model.Contract;
import com.finche.db.repository.ContractRepository;
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
public class ContractService{

   @Autowired
    private ContractRepository contractRepository;

    public Optional<Contract> create(Contract contract) {
        return Optional.ofNullable(contractRepository.save(contract));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Contract>> findAll(Pageable pageable) {
        return contractRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Contract>> findOneById(String id) {
        return contractRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Contract>> findAll() {
        return contractRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String contractId) {
        contractRepository
                .findOneById(contractId)
                .thenAccept(contract -> {
                    contractRepository.delete(contract);
                }).exceptionally(throwable -> {
            log.error("Unable to delete contract", throwable);
            return null;
                });
    }
}
