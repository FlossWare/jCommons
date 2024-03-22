package org.flossware.commons.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;
import org.flossware.commons.AbstractBase;

/**
 * Proxies calls to methods.
 *
 * @author sfloess
 */
public class ObjectProxy extends AbstractBase implements InvocationHandler {
    private final Object proxy;

    private Object getProxy() {
        return proxy;
    }

    public ObjectProxy(final Object toProxy) {
        proxy = Objects.requireNonNull(toProxy, "Must provide an instance to proxy!");
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return method.invoke(getProxy(), args);
    }
}
