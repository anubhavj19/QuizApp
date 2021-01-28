package com.crio.qa.repositories;

import com.crio.qa.models.ModuleEntity;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<ModuleEntity, String> {

  @Query("{ 'moduleId' : ?0 }")
  List<ModuleEntity> getModuleDetails(String moduleId);
}

