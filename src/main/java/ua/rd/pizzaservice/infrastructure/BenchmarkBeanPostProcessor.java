package ua.rd.pizzaservice.infrastructure;

import jdk.nashorn.internal.runtime.regexp.joni.Warnings;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Kostiantyn_Minkov on 10/11/2016.
 */
public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

    @Override
    @SuppressWarnings("Duplicates")
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        final Class<?> type = o.getClass();
        if ( !beanContainsMethodAnnotation(Benchmark.class, type)) {
            return o;
        }
        final Object prevBean = o;
        o = Proxy.newProxyInstance(
                type.getClassLoader(),
                type.getInterfaces(),
                (proxy, method, args) -> {
                    final Method beanMethod = type.getMethod(method.getName(), method.getParameterTypes());
                    if (beanMethod.isAnnotationPresent(Benchmark.class)) {
                        final Benchmark annotation = beanMethod.getAnnotation(Benchmark.class);
                        if (annotation.value()) {
                            final long before = System.nanoTime();
                            Object returnValue = beanMethod.invoke(prevBean, args);
                            System.out.println("Method " + s + ": " + (System.nanoTime() - before));
                            return returnValue;
                        }
                    }
                    return beanMethod.invoke(prevBean, args);
                });
        return o;
    }

    private boolean beanContainsMethodAnnotation(Class<? extends Annotation> annotationType,
                                                 Class<?> beanType) {
        for (Method method : beanType.getMethods()) {
            if (method.isAnnotationPresent(annotationType)) {
                return true;
            }
        }
        return false;
    }
}
