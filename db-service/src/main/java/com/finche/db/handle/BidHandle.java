package com.finche.db.handle;

import com.finche.db.model.Bid;
import com.finche.db.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class BidHandle {
    public static Function<Throwable, ResponseEntity> handleGetBidsFailure = throwable -> {
        log.error("Unable to retrieve Bids", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    public static Function<Optional<Bid>, ResponseEntity> mapMaybeBidToResponse = maybeBid -> maybeBid
            .<ResponseEntity>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    public static Function<String, Function<Throwable, ResponseEntity>> handleGetBidFailure = bidId -> throwable -> {
        log.error(String.format("Unable to retrieve bid for id: %s", bidId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
