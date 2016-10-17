package ua.rd.pizzaservice.domain;


import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {

    NEW, IN_PROGRESS, CANCELED, DONE;

    private enum Transition {

        FROM_NEW(NEW, IN_PROGRESS, CANCELED),
        FROM_IN_PROGRESS(IN_PROGRESS, DONE),
        FROM_CANCELED(CANCELED),
        FROM_DONE(DONE);

        OrderStatus from;
        EnumSet<OrderStatus> to;

        Transition(OrderStatus from, OrderStatus ... to) {
            this.from = from;
            this.to = EnumSet.noneOf(OrderStatus.class);
            this.to.addAll(Arrays.asList(to));
        }

        static final Map<OrderStatus, EnumSet<OrderStatus>> transitions = new HashMap<>();

        static {
            for (Transition transition : Transition.values()) {
                transitions.put(transition.from, EnumSet.copyOf(transition.to));
            }
        }
    }

    public boolean canChangeToStatus(OrderStatus status) {
        return Transition.transitions.get(this).contains(status);
    }
}
