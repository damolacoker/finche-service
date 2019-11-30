package com.finche.db.service;

import com.finche.db.model.User;
import com.finche.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Optional<User> create(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<User>> findAll(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<User>> findOneById(String id) {
        return userRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<User>> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username).thenApply(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<User>> findAll() {
        return userRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String userName){
        userRepository.findOneByUsername(userName)
                .thenAccept(user -> {
                    userRepository.delete(user);
                }).exceptionally(
                        throwable -> {
                            log.error("Unable to delete user", throwable);
                            return null;
                        });
    }
}
