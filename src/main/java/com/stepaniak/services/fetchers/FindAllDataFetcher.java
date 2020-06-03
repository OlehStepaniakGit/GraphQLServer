package com.stepaniak.services.fetchers;

import com.stepaniak.entities.DataEntity;
import com.stepaniak.repositories.DataRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllDataFetcher implements DataFetcher<List<DataEntity>> {
    @Autowired
    private DataRepository repository;

    @Override
    public List<DataEntity> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return repository.findAll();
    }
}
