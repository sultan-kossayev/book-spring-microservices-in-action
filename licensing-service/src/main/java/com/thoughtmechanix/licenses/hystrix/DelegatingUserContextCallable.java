package com.thoughtmechanix.licenses.hystrix;

import com.thoughtmechanix.licenses.filters.UserContext;
import com.thoughtmechanix.licenses.filters.UserContextHolder;

import java.util.concurrent.Callable;

public class DelegatingUserContextCallable<V> implements Callable<V> {

    private final Callable<V> delegate;

    private UserContext originalUserCtx;

    public DelegatingUserContextCallable(Callable<V> delegate, UserContext userContext) {
        this.delegate = delegate;
        this.originalUserCtx = userContext;
    }

    public V call() throws Exception {
        UserContextHolder.setContext(originalUserCtx);

        try {
            return delegate.call();
        }finally {
            originalUserCtx = null;
        }
    }
}
