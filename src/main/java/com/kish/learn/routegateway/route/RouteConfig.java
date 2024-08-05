package com.kish.learn.routegateway.route;

import com.kish.learn.routegateway.route.dao.RequestFilterDAO;
import com.kish.learn.routegateway.route.dao.RequestPredicateDAO;
import com.kish.learn.routegateway.route.dao.RouteDAO;
import com.kish.learn.routegateway.route.enumeration.FilterType;
import com.kish.learn.routegateway.route.enumeration.HTTPMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.server.mvc.filter.AfterFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.function.*;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.method;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class RouteConfig {
    /*
    // separate gateway it can be reactive...
* /ui

// non reactive gateway
* /web
// there shouldn't be any api related calls in the bff gateway...
* /api

// Predefined before filter
// validate the jwt token
// validate the csrf token for post,put and delete
// add the X-tracker-id if not present

//Predefined after filter
// remove duplicate sensitive header
// set cache
// set CSP
// set cors header

     */

    @Autowired
    private RouteDAORepository routeRepo;

    @Bean
    @RefreshScope
    public RouterFunction<ServerResponse> routes() {
        RouterFunctions.Builder route = route();
        routeRepo.findAll().stream()
                .map(this::createRoute)
                .forEach(route::add);
        return route.build();
    }

    private RouterFunction<ServerResponse> createRoute(RouteDAO routeDAO) {

        String identifier = routeDAO.getRouteIdentifier();
        //todo is feature flag enabled then pull both the blue
        // create 2 routes one with blue and its header request predicate
        // if weightage is set create one route with weightage conditions request predicate
        RouterFunctions.Builder route = route(identifier)
                .route(setRequestPredicate(routeDAO), http(URI.create(routeDAO.getRouteUriBlue())));
        //add before filters....
        routeDAO.getRequestFilterDAOS().stream().filter(t -> t.getFilterType() == FilterType.BEFORE)
                .map(this::setRequestBeforeFilter)
                .filter(Objects::nonNull)
                .forEach(route::before);


        //add after filters....
        routeDAO.getRequestFilterDAOS().stream().filter(t -> t.getFilterType() == FilterType.AFTER)
                .map(this::setRequestAfterFilter)
                .filter(Objects::nonNull)
                .forEach(route::after);
        return route.build();
    }


    private BiFunction<ServerRequest, ServerResponse, ServerResponse> setRequestAfterFilter(RequestFilterDAO requestFilterDAO) {
        return switch (requestFilterDAO.getSelectedFilter()){
            case ADD_RESPONSE_HEADERS ->  AfterFilterFunctions.addResponseHeader(requestFilterDAO.getSelectKey() , requestFilterDAO.getSelectValue());
            case REMOVE_RESPONSE_HEADERS -> AfterFilterFunctions.removeResponseHeader(requestFilterDAO.getSelectKey());
            case DE_DUPE_RESPONSE_HEADERS -> AfterFilterFunctions.dedupeResponseHeader(requestFilterDAO.getSelectKey());
            case SET_STATUS_CODE -> AfterFilterFunctions.setStatus(requestFilterDAO.getSelectValue());
            default -> null;
        };
    }


    private Function<ServerRequest, ServerRequest> setRequestBeforeFilter(RequestFilterDAO requestFilterDAO) {
        return switch (requestFilterDAO.getSelectedFilter()){
            case STRIP_PREFIX ->  stripPrefix(requestFilterDAO);
            case ADD_REQUEST_HEADER -> BeforeFilterFunctions.addRequestHeader(requestFilterDAO.getSelectKey() , requestFilterDAO.getSelectValue());
            case ADD_REQUEST_PARAM -> BeforeFilterFunctions.addRequestParameter(requestFilterDAO.getSelectKey() , requestFilterDAO.getSelectValue());
            case REMOVE_REQUEST_HEADER -> BeforeFilterFunctions.removeRequestHeader(requestFilterDAO.getSelectKey());
            case REMOVE_REQUEST_PARAM -> BeforeFilterFunctions.removeRequestParameter(requestFilterDAO.getSelectKey());
            case PREFIX_PATH ->  BeforeFilterFunctions.prefixPath(requestFilterDAO.getSelectValue());
            case REQUEST_HEADER_SIZE -> BeforeFilterFunctions.requestHeaderSize(DataSize.parse(requestFilterDAO.getSelectValue()));
            default -> null;
        };
    }

    private static Function<ServerRequest, ServerRequest> stripPrefix(RequestFilterDAO requestFilterDAO) {
        if(requestFilterDAO.getSelectValue() != null)
            return BeforeFilterFunctions.stripPrefix(Integer.parseInt(requestFilterDAO.getSelectValue()));
         else
            return BeforeFilterFunctions.stripPrefix();
    }

    private RequestPredicate setRequestPredicate(RouteDAO routesItem) {

        //default to add
        RequestPredicate reqpredicate =
                method(convertToHttpMethod(routesItem.getMethod()))
                .and(path(routesItem.getPath()));

        if(routesItem.getRequestPredicateDAOS() != null && routesItem.getRequestPredicateDAOS().size() > 0) {
            for (RequestPredicateDAO predicatesItem : routesItem.getRequestPredicateDAOS()) {
                RequestPredicate requestPredicate = fetchedRequestPredicate(predicatesItem);
                if(requestPredicate != null) {
                    reqpredicate = reqpredicate.and(requestPredicate);
                }
            }
        }
        return reqpredicate;
    }

    private HttpMethod convertToHttpMethod(HTTPMethod method) {
        return switch (method){
            case GET -> HttpMethod.GET;
            case POST -> HttpMethod.POST;
            case PUT -> HttpMethod.PUT;
            case DELETE -> HttpMethod.DELETE;
            case HEAD -> HttpMethod.HEAD;
            case OPTIONS -> HttpMethod.OPTIONS;
        };
    }

    private RequestPredicate fetchedRequestPredicate(RequestPredicateDAO requestPredicateDAO) {
        return switch (requestPredicateDAO.getRequestPredicateType()){
            case HOST -> GatewayRequestPredicates.host(requestPredicateDAO.getPredicateValue().split(","));
            case COOKIE -> GatewayRequestPredicates.cookie(requestPredicateDAO.getPredicateKey(), requestPredicateDAO.getPredicateValue());
            case HEADER -> GatewayRequestPredicates.header(requestPredicateDAO.getPredicateKey(), requestPredicateDAO.getPredicateValue());
            case AFTER_DATE_TIME -> GatewayRequestPredicates.after(ZonedDateTime.parse(requestPredicateDAO.getPredicateValue()));
            case BEFORE_DATE_TIME -> GatewayRequestPredicates.before(ZonedDateTime.parse(requestPredicateDAO.getPredicateValue()));
            case BETWEEN_DATE_TIME -> GatewayRequestPredicates.between(ZonedDateTime.parse(requestPredicateDAO.getPredicateKey()),ZonedDateTime.parse(requestPredicateDAO.getPredicateValue()));
            default -> null;
        };
    }
}
