package com.stepaniak.controllers;

import com.stepaniak.services.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GraphQLController {
    @Autowired
    private GraphQLService service;

    @PostMapping
    public ResponseEntity<Object> execute(@RequestBody String query) {
        ExecutionResult execute = service.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
