package com.thoughtmechanix.licenses.filters;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContextStorage = new ThreadLocal<>();

    public static UserContext getContext() {
        UserContext ctx = userContextStorage.get();
        if (ctx == null) {
            ctx = new UserContext();
            userContextStorage.set(ctx);
        }

        return ctx;
    }

    public static void setContext(UserContext ctx) {
        userContextStorage.set(ctx);
    }
}
