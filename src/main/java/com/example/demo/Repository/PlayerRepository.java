package com.example.demo.Repository;

import com.example.demo.Models.PlayerDb;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PlayerRepository extends ReactiveMongoRepository<PlayerDb, String> {

}
