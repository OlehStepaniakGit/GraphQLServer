package com.stepaniak.repositories;

import com.stepaniak.entities.DataEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends MongoRepository<DataEntity, String> {
    @Override
    Optional<DataEntity> findById(String s);

    @Override
    List<DataEntity> findAll();
}
