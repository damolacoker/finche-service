package com.finche.db.handle;

import com.finche.db.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class UserHandle {
    public static Function<Throwable, ResponseEntity> handleGetUsersFailure = throwable -> {
        log.error("Unable to retrieve users", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Optional<User>, ResponseEntity> mapMaybeUserToResponse = maybeUser -> maybeUser
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    public static Function<String, Function<Throwable, ResponseEntity>> handleGetUserFailure = userId -> throwable -> {
        log.error(String.format("Unable to retrieve user for id: %s", userId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
