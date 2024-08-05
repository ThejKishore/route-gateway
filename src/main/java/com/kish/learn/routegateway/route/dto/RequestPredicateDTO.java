package com.kish.learn.routegateway.route.dto;

import com.kish.learn.routegateway.route.enumeration.RequestPredicateType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * A RequestPredicateDAO.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestPredicateDTO{
    private Long id;
    @NotNull
    private String predicateName;
    @NotNull
    private String predicateKey;
    @NotNull
    private String predicateValue;
    @NotNull
    private RequestPredicateType requestPredicateType;
    private Long routeId;
}
