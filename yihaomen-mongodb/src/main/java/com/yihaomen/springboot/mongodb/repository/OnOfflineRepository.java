package com.yihaomen.springboot.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yihaomen.springboot.mongodb.entity.OnOfflineMsg;

public interface OnOfflineRepository extends MongoRepository<OnOfflineMsg, String> {
	
	List<OnOfflineMsg> findByUserId(Integer userId);
	
	List<OnOfflineMsg> findByUserIdAndMac(Integer userId, String mac);

}
