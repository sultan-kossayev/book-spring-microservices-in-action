package com.thoughtmechanix.licenses.filters;

import org.apache.commons.lang.StringUtils;

public class UserContext {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String USER_ID = "tmx-user-id";

    private String correlationId;
    private String userId;

    public String getCorrelationId() {
        return StringUtils.defaultString(correlationId);
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getUserId() {
        return StringUtils.defaultString(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
