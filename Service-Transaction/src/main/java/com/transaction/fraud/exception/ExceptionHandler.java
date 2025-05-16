package com.transaction.fraud.exception;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleBookError(UserException exceptionHandler) {
        return GraphQLError.newError()
                .message(exceptionHandler.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleAuthorError(TransactionException exceptionHandler) {
        return GraphQLError.newError()
                .message(exceptionHandler.getMessage())
                .build();
    }
}
