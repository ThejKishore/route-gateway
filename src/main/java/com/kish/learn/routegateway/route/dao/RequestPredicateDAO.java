package com.kish.learn.routegateway.route.dao;

import com.kish.learn.routegateway.route.enumeration.RequestPredicateType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A RequestPredicateDAO.
 */
@Entity
@Table(name = "request_predicate")
@Getter
@Setter
@NoArgsConstructor
public class RequestPredicateDAO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "predicate_name", nullable = false)
    private String predicateName;

    @NotNull
    @Column(name = "predicate_key", nullable = false)
    private String predicateKey;

    @NotNull
    @Column(name = "predicate_value", nullable = false)
    private String predicateValue;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "request_predicate_type", nullable = false)
    private RequestPredicateType requestPredicateType;

    @Column(name = "route_id")
    private Long routeId;

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestPredicateDAO)) {
            return false;
        }
        return getId() != null && getId().equals(((RequestPredicateDAO) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestPredicateDAO{" +
            "id=" + getId() +
            ", predicateName='" + getPredicateName() + "'" +
            ", predicateKey='" + getPredicateKey() + "'" +
            ", predicateValue='" + getPredicateValue() + "'" +
            ", requestPredicateType='" + getRequestPredicateType() + "'" +
            "}";
    }
}
