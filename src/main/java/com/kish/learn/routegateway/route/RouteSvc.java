package com.kish.learn.routegateway.route;

import com.kish.learn.routegateway.route.dao.RouteDAO;
import com.kish.learn.routegateway.route.dto.RouteDTO;
import com.kish.learn.routegateway.route.helper.Convertor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteSvc {

    private final RouteDAORepository routeDAORepository;


    public RouteSvc(RouteDAORepository routeDAORepository) {
        this.routeDAORepository = routeDAORepository;
    }

    public RouteDTO getRoute(Long routeId) {
        RouteDAO fetchedRouteDAO = routeDAORepository.findById(routeId)
                .orElseThrow(() -> new DataRetrievalFailureException("Route not found"));
        return Convertor.toDTO(fetchedRouteDAO);
    }

    public Set<RouteDTO> getAllRoutes() {
        return routeDAORepository.findAll().stream().map(Convertor::toDTO).collect(Collectors.toSet());
    }

    public RouteDTO updateRoute(RouteDTO routeDTO) {
            routeDAORepository.findById(routeDTO.getId())
                .orElseThrow(() -> new DataRetrievalFailureException("Route not found"));
            RouteDAO sentRouteDAO = Convertor.fromDTO(routeDTO);
            return Convertor.toDTO(routeDAORepository.save(sentRouteDAO));
    }

    public RouteDTO addRoute(RouteDTO routeDTO) {
        if(!routeDAORepository.existsByMethodAndPathAndRouteIdentifier(routeDTO.getMethod(),routeDTO.getPath(),routeDTO.getRouteIdentifier())) {
            RouteDAO routeDAO = routeDAORepository.save(Convertor.fromDTO(routeDTO));
            return Convertor.toDTO(routeDAO);
        }else{
            throw new DuplicateKeyException(String.format("The create request for route %s, %s , %s already exists",
                    routeDTO.getMethod() ,
                    routeDTO.getPath() ,
                    routeDTO.getRouteIdentifier()));
        }
    }
}
