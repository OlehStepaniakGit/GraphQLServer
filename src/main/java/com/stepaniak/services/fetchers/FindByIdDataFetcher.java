package com.stepaniak.services.fetchers;

import com.stepaniak.entities.DataEntity;
import com.stepaniak.repositories.DataRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindByIdDataFetcher implements DataFetcher<DataEntity> {
    @Autowired
    private DataRepository repository;

    @Override
    public DataEntity get(DataFetchingEnvironment dataFetchingEnvironment) {
        String id = dataFetchingEnvironment.getArgument("id");
        Optional<DataEntity> optional = repository.findById(id);

        return optional.orElse(null);

    }
}