package com.finche.db.service;

import com.finche.db.model.Company;
import com.finche.db.repository.CompanyRepository;
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
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public Optional<Company> create(Company company) {
        return Optional.ofNullable(companyRepository.save(company));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Company>> findAll(Pageable pageable) {
        return companyRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Company>> findOneById(String id) {
        return companyRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Company>> findAll() {
        return companyRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String companyId) {
        companyRepository
                .findOneById(companyId)
                .thenAccept(company -> {
                    companyRepository.delete(company);
                })
                .exceptionally(throwable -> {
                    log.error("Unable to delete company", throwable);
                    return null;
                });
    }
}
