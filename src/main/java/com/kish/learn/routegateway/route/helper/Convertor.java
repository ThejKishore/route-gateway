package com.kish.learn.routegateway.route.helper;

import com.kish.learn.routegateway.route.dao.RequestFilterDAO;
import com.kish.learn.routegateway.route.dao.RequestPredicateDAO;
import com.kish.learn.routegateway.route.dao.RouteDAO;
import com.kish.learn.routegateway.route.dto.RequestFilterDTO;
import com.kish.learn.routegateway.route.dto.RequestPredicateDTO;
import com.kish.learn.routegateway.route.dto.RouteDTO;

import java.util.stream.Collectors;


public class Convertor {


     public static final  RouteDAO fromDTO(RouteDTO dto){
        RouteDAO routeDAO = new RouteDAO();
        routeDAO.setId(dto.getId());
        routeDAO.setRouteIdentifier(dto.getRouteIdentifier());
        routeDAO.setPath(dto.getPath());
        routeDAO.setMethod(dto.getMethod());
        routeDAO.setRouteUriBlue(dto.getRouteUriBlue());
        routeDAO.setRouteUriGreen(dto.getRouteUriGreen());
        routeDAO.setIsWeight(dto.getIsWeight());
        routeDAO.setGreenWeight(dto.getGreenWeight());
        routeDAO.setBlueWeight(dto.getBlueWeight());
        routeDAO.setIsFeatureFlag(dto.getIsFeatureFlag());
        routeDAO.setFeatureFlagKey(dto.getFeatureFlagKey());
        routeDAO.setFeatureFlagValue(dto.getFeatureFlagValue());
        routeDAO.setRequestFilterDAOS(dto.getRequestFilterDTOS().stream()
                .map(Convertor::fromDTO)
                .collect(Collectors.toSet()));
        routeDAO.setRequestPredicateDAOS(dto.getRequestPredicateDTOS().stream()
                .map(Convertor::fromDTO)
                .collect(Collectors.toSet()));
        return routeDAO;
    }


    public static final  RouteDTO toDTO(RouteDAO routeDAO){
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId(routeDAO.getId());
        routeDTO.setRouteIdentifier(routeDAO.getRouteIdentifier());
        routeDTO.setPath(routeDAO.getPath());
        routeDTO.setMethod(routeDAO.getMethod());
        routeDTO.setRouteUriBlue(routeDAO.getRouteUriBlue());
        routeDTO.setRouteUriGreen(routeDAO.getRouteUriGreen());
        routeDTO.setIsWeight(routeDAO.getIsWeight());
        routeDTO.setGreenWeight(routeDAO.getGreenWeight());
        routeDTO.setBlueWeight(routeDAO.getBlueWeight());
        routeDTO.setIsFeatureFlag(routeDAO.getIsFeatureFlag());
        routeDTO.setFeatureFlagKey(routeDAO.getFeatureFlagKey());
        routeDTO.setFeatureFlagValue(routeDAO.getFeatureFlagValue());
        routeDTO.setRequestFilterDTOS(routeDAO.getRequestFilterDAOS().stream()
                .map(Convertor::toDTO)
                .collect(Collectors.toSet()));
        routeDTO.setRequestPredicateDTOS(routeDAO.getRequestPredicateDAOS().stream()
                .map(Convertor::toDTO)
                .collect(Collectors.toSet()));
        return routeDTO;
    }

    public static final  RequestPredicateDAO fromDTO(RequestPredicateDTO dto){
        RequestPredicateDAO requestPredicateDAO = new RequestPredicateDAO();
        requestPredicateDAO.setId(dto.getId());
        requestPredicateDAO.setPredicateName(dto.getPredicateName());
        requestPredicateDAO.setPredicateKey(dto.getPredicateKey());
        requestPredicateDAO.setPredicateValue(dto.getPredicateValue());
        requestPredicateDAO.setRequestPredicateType(dto.getRequestPredicateType());
        requestPredicateDAO.setRouteId(dto.getRouteId());
        return requestPredicateDAO;
    }

    public static final   RequestPredicateDTO toDTO(RequestPredicateDAO requestPredicateDAO){
        RequestPredicateDTO requestPredicateDTO = new RequestPredicateDTO();
        requestPredicateDTO.setId(requestPredicateDAO.getId());
        requestPredicateDTO.setPredicateName(requestPredicateDAO.getPredicateName());
        requestPredicateDTO.setPredicateKey(requestPredicateDAO.getPredicateKey());
        requestPredicateDTO.setPredicateValue(requestPredicateDAO.getPredicateValue());
        requestPredicateDTO.setRequestPredicateType(requestPredicateDAO.getRequestPredicateType());
        requestPredicateDTO.setRouteId(requestPredicateDAO.getRouteId());
        return requestPredicateDTO;
    }

    public static final   RequestFilterDAO fromDTO(RequestFilterDTO dto){
        RequestFilterDAO requestFilterDAO = new RequestFilterDAO();
        requestFilterDAO.setId(dto.getId());
        requestFilterDAO.setFilterName(dto.getFilterName());
        requestFilterDAO.setFilterType(dto.getFilterType());
        requestFilterDAO.setSelectedFilter(dto.getSelectedFilter());
        requestFilterDAO.setSelectKey(dto.getSelectKey());
        requestFilterDAO.setSelectValue(dto.getSelectValue());
        requestFilterDAO.setRouteId(dto.getRouteId());
        return requestFilterDAO;
    }

    public static final   RequestFilterDTO toDTO(RequestFilterDAO requestFilterDAO){
        RequestFilterDTO requestFilterDTO = new RequestFilterDTO();
        requestFilterDTO.setId(requestFilterDAO.getId());
        requestFilterDTO.setFilterName(requestFilterDAO.getFilterName());
        requestFilterDTO.setFilterType(requestFilterDAO.getFilterType());
        requestFilterDTO.setSelectedFilter(requestFilterDAO.getSelectedFilter());
        requestFilterDTO.setSelectKey(requestFilterDAO.getSelectKey());
        requestFilterDTO.setSelectValue(requestFilterDAO.getSelectValue());
        requestFilterDTO.setRouteId(requestFilterDAO.getRouteId());
        return requestFilterDTO;
    }

}
