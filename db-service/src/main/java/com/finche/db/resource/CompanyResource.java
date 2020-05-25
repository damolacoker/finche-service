package com.finche.db.resource;

import com.finche.db.client.NotificationServiceClient;
import com.finche.db.handle.CompanyHandle;
import com.finche.db.model.Company;
import com.finche.db.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/company/")
@Api(value = "Company Resource REST Endpoint")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private NotificationServiceClient notificationServiceClient;

    @ApiOperation(value = "Create Company")
    @PostMapping(value = "/createcompany", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createCompany(@Valid @RequestBody Company company) {
        try {
            log.info("Creating user: {}", company);
            companyService.create(company).ifPresent(companyCreated -> {
                log.info("User created: {}", companyCreated.getName());
            });
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/createcompanyv2", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createCompanyv2(@Valid @RequestBody Company company) {
        companyService
                .createWithCompletableFuture(company)
                .thenAcceptAsync(comp -> notificationServiceClient.sendNotification(comp))
                .exceptionally(CompanyHandle.handleCreateCompanyFailure);
        return null;
    }

    @ApiOperation(value = "Get Companies")
    @GetMapping(value = "/getcompanies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getCompanies(final Pageable paging) {
        return companyService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(CompanyHandle.handleGetCompaniesFailure);
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getMessage() {
        return "Hello world";
    }

    @GetMapping(value = "/getcompany/{companyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getCompanyById(@PathVariable("companyId") final String companyId) {
        return companyService
                .findOneById(companyId)
                .thenApply(CompanyHandle.mapMaybeCompanyToResponse)
                .exceptionally(CompanyHandle.handleGetCompanyFailure.apply(companyId));
    }

    @PutMapping(value = "/delete/{companyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteCompany(@PathVariable final String companyId) {
        try {
            companyService.delete(companyId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
