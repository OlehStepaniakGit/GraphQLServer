package com.stepaniak.services;

import com.stepaniak.services.fetchers.*;
import graphql.GraphQL;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.GraphQLSchema;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class GraphQLService {

    @Value("classpath:data.graphqls")
    private Resource resource;

    private GraphQL graphQL;

    @Autowired
    private FindAllDataFetcher findAllDataFetcher;

    @Autowired
    private FindByIdDataFetcher findByIdDataFetcher;

    @Autowired
    private CreateDataFetcher createDataFetcher;

    @Autowired
    private UpdateDataFetcher updateDataFetcher;

    @Autowired
    private DeleteDataFetcher deleteDataFetcher;


    /**
     * GraphQL Schema loading.
     */
    @SneakyThrows
    @PostConstruct
    private void loading() {
        final File schemaFile = resource.getFile();

        TypeDefinitionRegistry typeDefinition = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinition, wiring);

        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findAll", findAllDataFetcher)
                        .dataFetcher("findById", findByIdDataFetcher))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("create", createDataFetcher)
                        .dataFetcher("update", updateDataFetcher)
                        .dataFetcher("delete", deleteDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
