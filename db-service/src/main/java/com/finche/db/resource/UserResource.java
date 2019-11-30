package com.finche.db.resource;

import com.finche.db.handle.UserHandle;
import com.finche.db.model.User;
import com.finche.db.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

import static com.finche.db.handle.UserHandle.mapMaybeUserToResponse;

@Slf4j
@RestController
@RequestMapping("/user/")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createuser", consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody User user){
        try {
            userService.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/getusers",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getUsers(final Pageable paging){
        return userService
                .findAll(paging)
                .<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(UserHandle.handleGetUsersFailure);
    }

    @GetMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getUserById(@PathVariable final String userId) {
        return userService
                .findOneById(userId)
                .thenApply(UserHandle.mapMaybeUserToResponse)
                .exceptionally(UserHandle.handleGetUserFailure.apply(userId));
    }

    @GetMapping(value = "/{username}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CompletableFuture<ResponseEntity> getUserByUserName(@PathVariable final String username) {
        return userService
                .findOneByUsername(username)
                .thenApply(UserHandle.mapMaybeUserToResponse)
                .exceptionally(UserHandle.handleGetUserFailure.apply(username));
    }

    @PutMapping(value = "/delete/{username}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteUser(@PathVariable final String username){
        try {
            userService.delete(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
