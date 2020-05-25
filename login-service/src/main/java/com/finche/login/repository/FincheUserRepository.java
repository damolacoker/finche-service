package com.finche.login.repository;

import com.finche.login.model.FincheUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FincheUserRepository extends MongoRepository<FincheUser, String> {
}
