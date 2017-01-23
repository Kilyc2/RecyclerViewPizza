package com.kiliancerdan.pizzashop.pizzamenu;

import com.kiliancerdan.pizzashop.model.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MenuInteractorImpl implements MenuInteractor {

    @Override
    public void requestPizzas(Callback callback) {
        List<Pizza> pizzaMenu = Arrays.asList(
        new Pizza("Margarita", "margherita", "Crea tu propia pizza. Sobre una base de salsa de " +
                "tomate y queso 100% mozzarella, añade los ingredientes que tú quieras."),

        new Pizza("Barbacoa", "bbq", "Salsa barbacoa, queso 100% mozzarella, ternera especiada, " +
                "cebolla, bacon y maíz."),

        new Pizza("Pepperoni", "pepperoni", "Salsa de tomate, extra de queso 100% mozzarella y " +
                "doble de pepperoni."),

        new Pizza("Carbonara", "carbonara", "Crema fresca, queso 100% mozzarella, bacon, " +
                "champiñón y cebolla."),

        new Pizza("4 Quesos", "four_cheese", "Salsa de tomate y mezcla de 4 quesos."),

        new Pizza("Hawaiana", "hawaiian", "Salsa de tomate, extra de queso 100% mozzarella, " +
                "doble de York y piña"));

        callback.getMenu(pizzaMenu);
    }
}
