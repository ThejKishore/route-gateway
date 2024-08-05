package com.kish.learn.routegateway.route.dao;

import com.kish.learn.routegateway.route.enumeration.HTTPMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "route_dao")
public class RouteDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "route_identifier", nullable = false)
    private String routeIdentifier;

    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private HTTPMethod method;

    @NotNull
    @Column(name = "route_uri_blue", nullable = false)
    private String routeUriBlue;

    @Column(name = "route_uri_green")
    private String routeUriGreen;

    @Column(name = "is_weight")
    private Boolean isWeight;

    @Column(name = "green_weight")
    private Integer greenWeight;

    @Column(name = "blue_weight")
    private Integer blueWeight;

    @Column(name = "is_feature_flag")
    private Boolean isFeatureFlag;

    @Column(name = "feature_flag_key")
    private String featureFlagKey;

    @Column(name = "feature_flag_value")
    private String featureFlagValue;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Set<RequestFilterDAO> requestFilterDAOS;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Set<RequestPredicateDAO> requestPredicateDAOS;

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

}