package ua.rd.pizzaservice.infrastructure;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */
public class InitialContext {

    private static Config config = new JavaConfig();

    public <T> T getInstance(String name) {
        Class<?> type = config.getImpl(name);
        try {
            return (T) type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
