package com.kish.learn.routegateway.route.enumeration;

/**
 * The SelectedFilter enumeration.
 */
public enum SelectedFilter {
    STRIP_PREFIX("stripPrefix",FilterType.BEFORE),
    ADD_REQUEST_HEADER("addRequestHeader",FilterType.BEFORE),
    ADD_REQUEST_PARAM("addRequestParam",FilterType.BEFORE),
    REMOVE_REQUEST_HEADER("removeRequestHeader",FilterType.BEFORE),
    REMOVE_REQUEST_PARAM("removeRequestParam",FilterType.BEFORE),
    PREFIX_PATH("prefixPath",FilterType.BEFORE),
    REQUEST_HEADER_SIZE("requestHeaderSize",FilterType.BEFORE),

    ADD_RESPONSE_HEADERS("addResponseHeaders",FilterType.AFTER),
    REMOVE_RESPONSE_HEADERS("removeResponseHeaders",FilterType.AFTER),
    DE_DUPE_RESPONSE_HEADERS("deDupeResponseHeaders",FilterType.AFTER),
    SET_STATUS_CODE("setStatusCode",FilterType.AFTER),
    DEFAULT("default",FilterType.AFTER)
    ;
    private String name;

    private FilterType filterType;

    SelectedFilter(String name , FilterType filterType) {
        this.name = name;
        this.filterType = filterType;
    }

    public String getName() {
        return name;
    }

    public FilterType getFilterType() {
        return filterType;
    }
}
