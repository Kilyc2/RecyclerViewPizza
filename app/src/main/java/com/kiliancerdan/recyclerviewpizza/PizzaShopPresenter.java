package com.kiliancerdan.recyclerviewpizza;

import com.kiliancerdan.pizzashop.pizzamenu.MenuInteractor;
import com.kiliancerdan.pizzashop.model.Pizza;
import com.kiliancerdan.pizzashop.pizzaorder.OrderInteractor;

import java.util.ArrayList;
import java.util.List;

public class PizzaShopPresenter implements MenuInteractor.Callback, OrderInteractor.Callback {

    private PizzaShopView view;
    private MenuInteractor menuInteractor;
    private OrderInteractor orderInteractor;
    private List<Pizza> menu;

    public PizzaShopPresenter(MenuInteractor menuInteractor,
                              OrderInteractor orderInteractor) {
        this.menuInteractor = menuInteractor;
        this.orderInteractor = orderInteractor;
    }

    public void setView(PizzaShopView view) {
        this.view = view;
    }

    void onStart() {
        menuInteractor.requestPizzas(this);
    }

    @Override
    public void getMenu(List<Pizza> menu) {
        this.menu = menu;
        view.showMenu(getMenuPizza());
    }

    private List<String> getMenuPizza() {
        List<String> menuPizza = new ArrayList<>();
        for (Pizza pizza : menu) {
            menuPizza.add(pizza.getName());
        }
        return menuPizza;
    }

    public void selectPizza(int position) {
        orderInteractor.requestPizza(menu.get(position), this);
    }

    @Override
    public void getPizza(Pizza pizza) {
        view.addPizza(pizza);
    }

    interface PizzaShopView {
        void showMenu(List<String> menuPizza);
        void addPizza(Pizza pizza);
    }
}
