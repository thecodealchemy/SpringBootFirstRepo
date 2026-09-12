package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.AppConfig;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppCacheRepository extends MongoRepository<AppConfig, ObjectId> {

}