package com.example.UserMicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.UserMicroservice.model.PaymentInfo;

public interface PaymentInfoRepository extends MongoRepository<PaymentInfo, String>{

}
