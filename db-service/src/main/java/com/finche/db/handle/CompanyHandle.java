package com.finche.db.handle;

import com.finche.db.model.Company;
import com.finche.db.model.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class CompanyHandle {
    public static Function<Throwable, ResponseEntity> handleGetCompaniesFailure = throwable -> {
        log.error("Unable to retrieve companies", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Optional<Company>, ResponseEntity> mapMaybeCompanyToResponse = maybeCompany -> maybeCompany
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    public static Function<String, Function<Throwable, ResponseEntity>> handleGetCompanyFailure = companyId -> throwable -> {
        log.error(String.format("Unable to retrieve company for id: %s", companyId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Throwable, Void> handleCreateCompanyFailure = throwable -> {
        log.error(String.format("Unable to create company for id: %s"), throwable);
        return null;
    };
}
