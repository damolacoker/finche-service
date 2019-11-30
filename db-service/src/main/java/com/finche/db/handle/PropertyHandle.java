package com.finche.db.handle;

import com.finche.db.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class PropertyHandle {
    public static Function<Throwable, ResponseEntity> handleGetPropertiesFailure = throwable -> {
        log.error("Unable to retrieve properties", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Optional<Property>, ResponseEntity> mapMaybePropertyToResponse = maybeProperty -> maybeProperty
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    public static Function<String, Function<Throwable, ResponseEntity>> handleGetPropertyFailure = propertyId -> throwable -> {
        log.error(String.format("Unable to retrieve property for id: %s", propertyId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
