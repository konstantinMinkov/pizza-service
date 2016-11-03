package ua.rd.pizzaservice.web.infrastructure;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.http.HttpServletRequest;


public class SimpleUrlHandlerMapping implements HandlerMapping, ApplicationContextAware {

    private ApplicationContext webContext;

    @Override
    public MyController getController(HttpServletRequest request) {
        final String uri = request.getRequestURI();
        final String controllerName = getControllerName(uri);

        return webContext.getBean(controllerName, MyController.class);
    }

    private String getControllerName(String uri) {
        return uri.substring(uri.lastIndexOf("/"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        webContext = applicationContext;
    }
}
