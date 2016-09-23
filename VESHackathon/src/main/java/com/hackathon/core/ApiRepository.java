package com.hackathon.core;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface ApiRepository<T extends ApiCollector> extends CrudRepository<T, ObjectId>{

}
