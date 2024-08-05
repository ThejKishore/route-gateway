package com.kish.learn.routegateway.route.dao;

import com.kish.learn.routegateway.route.enumeration.FilterType;
import com.kish.learn.routegateway.route.enumeration.SelectedFilter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A RequestFilterDAO.
 */
@Entity
@Table(name = "request_filter")
@Getter
@Setter
@NoArgsConstructor
public class RequestFilterDAO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotNull(message = "select filter name  can't be null")
    @Column(name = "filter_name", nullable = false)
    private String filterName;

    @NotNull(message = "select filter type can't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "filter_type", nullable = false)
    private FilterType filterType;

    @NotNull(message = "select filter can't be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "selected_filter", nullable = false)
    private SelectedFilter selectedFilter;

    @NotNull(message = "select key can't be null")
    @Column(name = "select_key", nullable = false)
    private String selectKey;

    @NotNull(message = "select value can't be null")
    @Column(name = "select_value", nullable = false)
    private String selectValue;

    @Column(name = "route_id")
    private Long routeId;

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestFilterDAO)) {
            return false;
        }
        return getId() != null && getId().equals(((RequestFilterDAO) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

}
