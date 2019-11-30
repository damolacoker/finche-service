package com.finche.db.handle;

import com.finche.db.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class ProjectHandle {
    public static Function<Throwable, ResponseEntity> handleGetProjectsFailure = throwable -> {
        log.error("Unable to retrieve projects", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Optional<Project>, ResponseEntity> mapMaybeProjectToResponse = maybeProject -> maybeProject
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    public static Function<String, Function<Throwable, ResponseEntity>> handleGetProjectFailure = projectId -> throwable -> {
        log.error(String.format("Unable to retrieve project for id: %s", projectId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
