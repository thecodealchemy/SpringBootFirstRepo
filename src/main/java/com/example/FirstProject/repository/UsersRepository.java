package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, ObjectId> {
    public User getUserByUsername(String username);
}
