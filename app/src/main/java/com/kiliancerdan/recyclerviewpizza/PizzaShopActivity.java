package com.kiliancerdan.recyclerviewpizza;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kiliancerdan.pizzashop.pizzamenu.MenuInteractor;
import com.kiliancerdan.pizzashop.pizzamenu.MenuInteractorImpl;
import com.kiliancerdan.pizzashop.model.Pizza;
import com.kiliancerdan.pizzashop.pizzaorder.OrderInteractor;
import com.kiliancerdan.pizzashop.pizzaorder.OrderInteractorImpl;

import java.util.ArrayList;
import java.util.List;

public class PizzaShopActivity extends AppCompatActivity implements PizzaShopPresenter.PizzaShopView {

    PizzaShopPresenter presenter;
    RecyclerView orderList;
    Spinner menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_shop_activity);
        MenuInteractor menuInteractor = new MenuInteractorImpl();
        OrderInteractor orderInteractor = new OrderInteractorImpl();
        presenter = new PizzaShopPresenter(menuInteractor, orderInteractor);
        presenter.setView(this);
        orderList = (RecyclerView) findViewById(R.id.list_order);
        orderList.setLayoutManager(new LinearLayoutManager(this));
        orderList.setHasFixedSize(true);
        orderList.setAdapter(new PizzaShopAdapter(new ArrayList<Pizza>()));
        orderList.setItemAnimator(new DefaultItemAnimator());
        menu = (Spinner) findViewById(R.id.pizza_list_spinner);
        Button addPizza = (Button) findViewById(R.id.add_pizza);
        if (addPizza != null) {
            addPizza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.selectPizza(menu.getSelectedItemPosition());
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void showMenu(List<String> menuPizza) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, menuPizza);
        menu.setAdapter(adapter);
    }

    @Override
    public void addPizza(Pizza pizza) {
        ((PizzaShopAdapter) orderList.getAdapter()).addItem(pizza);
        orderList.scrollToPosition(0);
    }
}
