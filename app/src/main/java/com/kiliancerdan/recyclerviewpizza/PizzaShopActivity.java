package com.kiliancerdan.recyclerviewpizza;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kiliancerdan.pizzashop.model.Pizza;
import com.kiliancerdan.pizzashop.pizzamenu.MenuInteractor;
import com.kiliancerdan.pizzashop.pizzamenu.MenuInteractorImpl;
import com.kiliancerdan.pizzashop.pizzaorder.OrderInteractor;
import com.kiliancerdan.pizzashop.pizzaorder.OrderInteractorImpl;

import java.util.List;

public class PizzaShopActivity extends AppCompatActivity implements PizzaShopPresenter.PizzaShopView,
        View.OnClickListener {

    PizzaShopPresenter presenter;
    RecyclerView recyclerOrderList;
    PizzaShopAdapter adapter;
    Spinner spinnerMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_shop_activity);

        MenuInteractor menuInteractor = new MenuInteractorImpl();
        OrderInteractor orderInteractor = new OrderInteractorImpl();
        presenter = new PizzaShopPresenter(menuInteractor, orderInteractor);
        presenter.setView(this);

        spinnerMenu = (Spinner) findViewById(R.id.pizza_list_spinner);

        Button addPizza = (Button) findViewById(R.id.add_pizza_button);
        addPizza.setOnClickListener(this);

        adapter = new PizzaShopAdapter(this);
        recyclerOrderList = (RecyclerView) findViewById(R.id.list_order);
        recyclerOrderList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void fillMenu(List<String> menuPizza) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, menuPizza);
        spinnerMenu.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_pizza_button:
                presenter.selectPizza(spinnerMenu.getSelectedItemPosition());
                break;
            case R.id.remove_pizza_button:
                Pizza pizza = (Pizza)v.getTag();
                adapter.removeItem(pizza);
        }
    }

    @Override
    public void addPizza(Pizza pizza) {
        adapter.addItem(pizza);
        recyclerOrderList.scrollToPosition(0);
    }
}
