package ua.rd.pizzaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Pizza {

    private Integer id;
    private String name;
    private Long price;
    private PizzaType type;
}
