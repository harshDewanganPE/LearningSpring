package com.harsh.sample.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.harsh.sample.entity.User;



public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);    

}
//user_name