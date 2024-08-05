package com.kish.learn.routegateway.route.dto;

import com.kish.learn.routegateway.route.enumeration.FilterType;
import com.kish.learn.routegateway.route.enumeration.SelectedFilter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * A RequestFilterDAO.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestFilterDTO {

    private Long id;
    @NotNull
    private String filterName;
    @NotNull
    private FilterType filterType;
    @NotNull
    private SelectedFilter selectedFilter;
    @NotNull
    private String selectKey;
    @NotNull
    private String selectValue;
    private Long routeId;
}
