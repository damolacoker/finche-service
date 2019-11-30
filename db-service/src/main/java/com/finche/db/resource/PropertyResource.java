package com.finche.db.resource;

import com.finche.db.handle.PropertyHandle;
import com.finche.db.model.Property;
import com.finche.db.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/property/")
public class PropertyResource {

    @Autowired
    private PropertyService propertyService;

    @PostMapping(value = "/createproperty", consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createProperty(@Valid @RequestBody Property property){
        try {
            propertyService.create(property);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping( value = "/getproperties" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getProperties(final Pageable paging){
        return propertyService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(PropertyHandle.handleGetPropertiesFailure);
    }

    @GetMapping(value = "/{propertyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getPropertyById(@PathVariable final String propertyId) {
        return propertyService
                .findOneById(propertyId)
                .thenApply(PropertyHandle.mapMaybePropertyToResponse)
                .exceptionally(PropertyHandle.handleGetPropertyFailure.apply(propertyId));
    }

    @PutMapping(value = "/delete/{propertyId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteProperty(@PathVariable final String propertyId){
        try {
            propertyService.delete(propertyId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
