package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findUserswithEmailOptIn(){
        Query query = new Query();
        query.addCriteria(Criteria.where("emailOptIn").is(true));
        return mongoTemplate.find(query, User.class);
    }
}
