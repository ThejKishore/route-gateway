package com.kish.learn.routegateway.route;

import com.kish.learn.routegateway.route.dao.RequestFilterDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RequestFilterDAORepository extends JpaRepository<RequestFilterDAO, Long> {
    Set<RequestFilterDAO> findAllByRouteId(Long routeId);
}