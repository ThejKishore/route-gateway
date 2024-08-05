package com.kish.learn.routegateway;

import com.kish.learn.routegateway.route.RouteSvc;
import com.kish.learn.routegateway.route.dto.RequestFilterDTO;
import com.kish.learn.routegateway.route.dto.RequestPredicateDTO;
import com.kish.learn.routegateway.route.dto.RouteDTO;
import com.kish.learn.routegateway.route.enumeration.FilterType;
import com.kish.learn.routegateway.route.enumeration.HTTPMethod;
import com.kish.learn.routegateway.route.enumeration.RequestPredicateType;
import com.kish.learn.routegateway.route.enumeration.SelectedFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class RouteGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouteGatewayApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx, RouteSvc routeSvc) {
        return args -> {
              RouteDTO getRoute = RouteDTO.builder()
                      .path("/studentui")
                      .method(HTTPMethod.GET)
                      .routeUriBlue(" http://localhost:5173/")
                      .isFeatureFlag(Boolean.FALSE)
                      .isWeight(Boolean.FALSE)
                      .routeIdentifier("student-ui-prefix")
                      .requestFilterDTO(RequestFilterDTO.builder()
                              .filterName("stripfilter")
                              .filterType(FilterType.BEFORE)
                              .selectedFilter(SelectedFilter.STRIP_PREFIX)
                              .selectKey("dummy")
                              .selectValue("1")
                              .build())
                      .requestFilterDTO(RequestFilterDTO.builder()
                              .filterName("addResponsefilter")
                              .filterType(FilterType.AFTER)
                              .selectedFilter(SelectedFilter.ADD_RESPONSE_HEADERS)
                              .selectKey("x-Response-header")
                              .selectValue("from-gateway")
                              .build())
                      .build();
//            routeSvc.addRoute(getRoute);
            routeSvc.getAllRoutes().stream().forEach(System.out::println);
        };

    }

}
