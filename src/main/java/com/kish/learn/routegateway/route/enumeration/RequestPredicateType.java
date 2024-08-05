package com.kish.learn.routegateway.route.enumeration;

/**
 * The RequestPredicateType enumeration.
 */
public enum RequestPredicateType {
    AFTER_DATE_TIME("after"),
    BEFORE_DATE_TIME("before"),
    BETWEEN_DATE_TIME("between"),
    COOKIE("cookie"),
    HEADER("header"),
    HOST("host"),
    QUERY("query"),
    BODY("body"),
    ;

    private String name;

    RequestPredicateType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
