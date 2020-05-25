package com.finche.db.service;

import com.finche.db.model.Project;
import com.finche.db.repository.ProjectRepository;
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
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Optional<Project> create(Project project) {
        return Optional.ofNullable(projectRepository.save(project));
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Page<Project>> findAll(Pageable pageable) {
        return projectRepository.findAllBy(pageable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Optional<Project>> findOneById(String id) {
        return projectRepository
                .findOneById(id)
                .thenApplyAsync(Optional::ofNullable);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Project>> findAll() {
        return projectRepository.findAllBy();
    }

    @Async("threadPoolTaskExecutor")
    public void delete(String projectId) {
        projectRepository.findOneById(projectId)
                .thenAccept(project -> projectRepository.delete(project))
                .exceptionally(throwable -> {
                    log.error("Unable to delete project", throwable);
                    return null;
                });
    }
}
