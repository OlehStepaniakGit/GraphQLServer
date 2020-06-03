package com.stepaniak.services.fetchers;

import com.stepaniak.entities.DataEntity;
import com.stepaniak.repositories.DataRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateDataFetcher implements DataFetcher<DataEntity> {
    @Autowired
    private DataRepository repository;

    @Override
    public DataEntity get(DataFetchingEnvironment dataFetchingEnvironment) {
        String content = dataFetchingEnvironment.getArgument("content");
        DataEntity entity = new DataEntity(content);
        return repository.save(entity);
    }
}
