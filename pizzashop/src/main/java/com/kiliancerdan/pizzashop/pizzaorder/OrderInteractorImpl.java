package com.kiliancerdan.pizzashop.pizzaorder;

import com.kiliancerdan.pizzashop.model.Pizza;

public class OrderInteractorImpl implements OrderInteractor {

    @Override
    public void requestPizza(Pizza pizza, Callback callback) {
        callback.getPizza(new Pizza(pizza));
    }
}
