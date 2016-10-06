package ua.rd.pizzaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Kostiantyn_Minkov on 10/4/2016.
 */

@Data
@AllArgsConstructor
public class Pizza {

        private Integer id;
        private String name;
        private BigDecimal price;
        private PizzaType type;
}
