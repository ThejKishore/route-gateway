package com.kish.learn.routegateway.route;

import com.kish.learn.routegateway.route.dao.RouteDAO;
import com.kish.learn.routegateway.route.enumeration.HTTPMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteDAORepository extends JpaRepository<RouteDAO, Long> {
    boolean existsByMethodAndPathAndRouteIdentifier(HTTPMethod method, String path, String routeIdentifier);
    RouteDAO findByMethodAndPathAndRouteIdentifier(HTTPMethod method, String path, String routeIdentifier);
}