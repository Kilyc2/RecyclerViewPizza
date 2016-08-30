package com.kiliancerdan.pizzashop.pizzaorder;

import com.kiliancerdan.pizzashop.model.Pizza;

public interface OrderInteractor {

    void requestPizza(Pizza pizza, Callback callback);

    interface Callback {
        void getPizza(Pizza pizza);
    }
}
