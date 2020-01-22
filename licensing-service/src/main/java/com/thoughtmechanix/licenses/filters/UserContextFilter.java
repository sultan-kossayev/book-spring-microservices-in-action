package com.thoughtmechanix.licenses.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        UserContextHolder.getContext().setCorrelationId(httpReq.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(httpReq.getHeader(UserContext.USER_ID));

        chain.doFilter(request, response);
    }
}
