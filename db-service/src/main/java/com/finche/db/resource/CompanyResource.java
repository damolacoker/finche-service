package com.finche.db.resource;

import com.finche.db.handle.CompanyHandle;
import com.finche.db.model.Company;
import com.finche.db.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/company/")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/createcompany", consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createCompany(@Valid @RequestBody Company company){
        try {
            companyService.create(company);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping( value = "/getcompanies" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getCompanies(final Pageable paging){
        return companyService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(CompanyHandle.handleGetCompaniesFailure);
    }

    @GetMapping(value = "/{companyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getCompanyById(@PathVariable final String companyId) {
        return companyService
                .findOneById(companyId)
                .thenApply(CompanyHandle.mapMaybeCompanyToResponse)
                .exceptionally(CompanyHandle.handleGetCompanyFailure.apply(companyId));
    }

    @PutMapping(value = "/delete/{companyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteCompany(@PathVariable final String companyId){
        try {
            companyService.delete(companyId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
