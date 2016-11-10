package ua.rd.pizzaservice.web.infrastructure;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class AnnotationDrivenHandlerMapping implements HandlerMapping, ApplicationContextAware {

    private Map<String, MyController> pathToControllerMap = new HashMap<>();

    @Override
    public MyController getController(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        final String controllerName = getControllerName(uri);

        return pathToControllerMap.get(controllerName);
    }

    private String getControllerName(String uri) {
        return uri.substring(uri.indexOf('/', uri.indexOf("servlet")));
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        mapControllersToPaths(appContext);
    }

    public void mapControllersToPaths(ApplicationContext appContext) {
        Map<String, Object> controllers
                = appContext.getBeansWithAnnotation(Controller.class);
        controllers.values().forEach(this::mapControllerToPaths);
    }

    private void mapControllerToPaths(Object controller) {
        final Class<?> clazz = controller.getClass();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(CustomRequestMapping.class)) {
                mapMethodToPath(method, controller);
            }
        }
    }

    private void mapMethodToPath(Method method, Object controller) {
        checkMethod(method);
        final CustomRequestMapping annotation = method.getAnnotation(CustomRequestMapping.class);
        final String path = annotation.path();
        pathToControllerMap.put(path,
                (HttpServletRequest request, HttpServletResponse response) -> {
                    try {
                        method.invoke(controller, request, response);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void checkMethod(Method method) {
        final Parameter[] params = method.getParameters();
        if ( !hasTwoParams(params)) {
            throw new IllegalComponentStateException("Method should have only 1 argument.");
        }
        if ( !isHttpServletRequestType(params[0])
                || !isHttpServletResponseType(params[1])) {
            throw new IllegalComponentStateException("Method should have argument of HttpServletRequest type");
        }
    }

    private boolean isHttpServletRequestType(Parameter param) {
        return param.getType().equals(HttpServletRequest.class);
    }

    private boolean isHttpServletResponseType(Parameter param) {
        return param.getType().equals(HttpServletResponse.class);
    }

    private boolean hasTwoParams(Parameter[] params) {
        return params.length == 2;
    }
}
