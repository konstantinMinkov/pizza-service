package ua.rd.pizzaservice.infrastructure;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class ApplicationContext implements Context {

    private final Config config;
    private final Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) {

        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(type);

        if (bean != null) {
            return (T) bean;
        }

        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean();
        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createBeanProxy();
        bean = builder.build();

        beans.put(beanName, bean);
        return (T) bean;
    }

    private class BeanBuilder {

        private final Class<?> type;
        private Object bean;

        BeanBuilder(Class<?> type) {
            this.type = type;
        }

        void createBean() {
            final Constructor<?> constructor =
                    type.getConstructors()[0];
            final Object[] params;

            if (constructor.getParameterCount() == 0) {
                bean = instantiate(constructor);
            } else {
                params = instantiateAllParams(constructor);
                bean = instantiate(constructor, params);
            }
        }

        Object build() {
            return bean;
        }

        private <T> T instantiate(Constructor<?> constructor, Object ... params) {
            try {
                return (T) constructor.newInstance(params);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private Object[] instantiateAllParams(Constructor<?> constructor) {
            final Class<?>[] paramTypes = constructor.getParameterTypes();
            final Object[] params = new Object[paramTypes.length];

            for (int i = 0; i < paramTypes.length; i++) {
                final String paramName = convertTypeToBeanName(paramTypes[i]);
                params[i] = getBean(paramName);
            }

            return params;
        }

        private String convertTypeToBeanName(Class<?> type) {
            final String typeName = type.getSimpleName();
            return Character.toLowerCase(typeName.charAt(0))
                    + typeName.substring(1);
        }

        void callInitMethod() {
            try {
                final Method method = type.getMethod("init");
                try {
                    method.invoke(bean);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException ignored) { }
        }

        void callPostCreateMethod() {
            for (Method method : type.getMethods()) {
                if (method.isAnnotationPresent(PostCreate.class)) {
                    try {
                        method.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        void createBeanProxy() {
            if ( !beanContainsMethodAnnotation(Benchmark.class)) {
                return;
            }
            final Object prevBean = bean;
            bean = Proxy.newProxyInstance(
                    type.getClassLoader(),
                    type.getInterfaces(),
                    (proxy, method, args) -> {
                        final Method beanMethod = type.getMethod(method.getName(), method.getParameterTypes());
                        if (beanMethod.isAnnotationPresent(Benchmark.class)) {
                            final Benchmark annotation = beanMethod.getAnnotation(Benchmark.class);
                            if (annotation.value()) {
                                final long before = System.nanoTime();
                                Object returnValue = beanMethod.invoke(prevBean, args);
                                System.out.println(System.nanoTime() - before);
                                return returnValue;
                            }
                        }
                        return beanMethod.invoke(prevBean, args);
                    });
        }

        private boolean beanContainsMethodAnnotation(Class<? extends Annotation> annotationType) {
            for (Method method : type.getMethods()) {
                if (method.isAnnotationPresent(annotationType)) {
                    return true;
                }
            }
            return false;
        }
    }
}
