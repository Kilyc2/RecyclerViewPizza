package com.kiliancerdan.pizzashop.pizzamenu;

import com.kiliancerdan.pizzashop.model.Pizza;

import java.util.List;

public interface MenuInteractor {

    void requestPizzas(Callback callback);

    interface Callback {
        void getMenu(List<Pizza> menu);
    }

}
