package com.finche.db.resource;

import com.finche.db.handle.BidHandle;
import com.finche.db.handle.ProjectHandle;
import com.finche.db.model.Bid;
import com.finche.db.model.Project;
import com.finche.db.service.BidService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bid/")
@Api(value = "Bid Resource REST Endpoint")
public class BidResource {

    @Autowired
    private BidService bidService;

    @PostMapping(value = "/createbid", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createBid(@Valid @RequestBody Bid bid) {
        try {
            bidService.create(bid);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/getbids", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getBids(final Pageable paging) {
        return bidService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(ProjectHandle.handleGetProjectsFailure);
    }

    @GetMapping(value = "/{bidId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getBidById(@PathVariable final String bidId) {
        return bidService
                .findOneById(bidId)
                .thenApply(BidHandle.mapMaybeBidToResponse)
                .exceptionally(BidHandle.handleGetBidFailure.apply(bidId));
    }

    @GetMapping(value = "/getbidbyname/{projectname}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getBidByName(@PathVariable final String projectname) {
        return bidService
                .findOneByName(projectname)
                .thenApply(BidHandle.mapMaybeBidToResponse)
                .exceptionally(BidHandle.handleGetBidFailure.apply(projectname));
    }

    @PutMapping(value = "/delete/{bidId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteBidId(@PathVariable final String bidId) {
        try {
            bidService.delete(bidId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
