package com.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class RouteValidator.
 */
@Component
public class RouteValidator {

    /** The Constant openApiEndpoints. */
    public static final List<String> openApiEndpoints = List.of(
            "/auth/addUser",
            "/auth/addExpert",
            "/auth/generateToken",
            "/auth/validateToken/**","/user/register","/expert/addissue","/eureka"
        
            
    );

    /** The is secured. */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
