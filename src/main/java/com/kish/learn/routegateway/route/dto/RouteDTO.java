package com.kish.learn.routegateway.route.dto;

import com.kish.learn.routegateway.route.enumeration.HTTPMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RouteDTO {
    private Long id;
    @NotNull
    private String routeIdentifier;
    @NotNull
    private String path;
    @NotNull
    private HTTPMethod method;
    @NotNull
    private String routeUriBlue;
    private String routeUriGreen;
    private Boolean isWeight;
    private Integer greenWeight;
    private Integer blueWeight;
    private Boolean isFeatureFlag;
    private String featureFlagKey;
    private String featureFlagValue;
    @Singular
    private Set<RequestFilterDTO> requestFilterDTOS;
    @Singular
    private Set<RequestPredicateDTO> requestPredicateDTOS;

}