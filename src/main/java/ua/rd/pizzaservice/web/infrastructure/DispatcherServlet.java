package ua.rd.pizzaservice.web.infrastructure;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        Deque<String> contexts = getContextsNames();

        final String webContextConfigLocation = getInitParameter("contextConfigLocation");
        webContext = new ClassPathXmlApplicationContext(
                new String[]{webContextConfigLocation},
                createContext(contexts));
    }

    private Deque<String> getContextsNames() {
        final String contextConfigLocation =
                getServletContext().getInitParameter("contextConfigLocation");
        final String[] contexts = contextConfigLocation.split(" ");

        return new LinkedList<>(Arrays.asList(contexts));
    }

    private ConfigurableApplicationContext createContext(Deque<String> contexts) {
        final String context = contexts.removeLast();
        if (contexts.isEmpty()) {
            return new ClassPathXmlApplicationContext(new String[]{context});
        }
        return new ClassPathXmlApplicationContext(new String[]{context},
                createContext(contexts));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        HandlerMapping handlerMapping = webContext.getBean("handlerMappingStrategy",
                HandlerMapping.class);

        MyController controller = handlerMapping.getController(request);//getController(controllerName);

        if (controller != null) {
            controller.handleRequest(request, response);
        }
    }

    @Override
    public void destroy() {
        closeContext(webContext);
    }

    private void closeContext(ConfigurableApplicationContext context) {
        if (context == null) return;
        context.close();
        closeContext((ConfigurableApplicationContext) context.getParent());
    }
}
