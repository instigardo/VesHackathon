package com.hackathon.parser;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

public interface ResponseRepository<T extends ResponseCollector> extends CrudRepository<T, ObjectId>{

}
