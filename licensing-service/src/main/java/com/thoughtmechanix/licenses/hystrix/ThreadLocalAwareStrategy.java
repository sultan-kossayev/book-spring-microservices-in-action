package com.thoughtmechanix.licenses.hystrix;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import com.thoughtmechanix.licenses.filters.UserContextHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalAwareStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy existingStrategy;

    public ThreadLocalAwareStrategy(HystrixConcurrencyStrategy existing) {
        this.existingStrategy = existing;
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        return existingStrategy == null ? super.getBlockingQueue(maxQueueSize) :
                existingStrategy.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        return existingStrategy == null ? super.getRequestVariable(rv) :
                existingStrategy.getRequestVariable(rv);
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize
            , HystrixProperty<Integer> maxPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
                                            BlockingQueue<Runnable> workQueue) {
        return existingStrategy == null ? super.getThreadPool(threadPoolKey, corePoolSize, maxPoolSize, keepAliveTime,
                unit, workQueue) :
                existingStrategy.getThreadPool(threadPoolKey, corePoolSize, maxPoolSize,
                        keepAliveTime, unit, workQueue);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return existingStrategy == null ? super.wrapCallable(new DelegatingUserContextCallable<>(callable,
                UserContextHolder.getContext())) :
                existingStrategy.wrapCallable(new DelegatingUserContextCallable<>(callable,
                        UserContextHolder.getContext()));
    }
}
