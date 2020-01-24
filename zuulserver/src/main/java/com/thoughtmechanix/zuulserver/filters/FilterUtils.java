package com.thoughtmechanix.zuulserver.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";

    public String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        String id = ctx.getRequest().getHeader(CORRELATION_ID);

        return StringUtils.isEmpty(id)? ctx.getZuulRequestHeaders().get(CORRELATION_ID) : id;
    }

    public void setCorrelationId(String id) {
        RequestContext.getCurrentContext().addZuulRequestHeader(CORRELATION_ID, id);
    }
}
