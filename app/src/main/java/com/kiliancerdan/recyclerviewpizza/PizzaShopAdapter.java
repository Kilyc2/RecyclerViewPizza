package com.kiliancerdan.recyclerviewpizza;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiliancerdan.pizzashop.model.Pizza;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class PizzaShopAdapter extends RecyclerView.Adapter<PizzaShopAdapter.PizzaViewHolder> {

    private List<Pizza> pizzas;
    private View.OnClickListener listener;

    PizzaShopAdapter(View.OnClickListener listener) {
        this.pizzas = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);

        holder.itemView.setTag(pizza);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaDescription.setText(pizza.getDescription());
        holder.closeCard.setTag(pizza);
        setImage(holder, pizza.getImage());
    }

    private void setImage(PizzaViewHolder holder, String pizza) {
        Context context = holder.itemView.getContext();
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(pizza, "drawable", context.getPackageName());
        Picasso.with(context).load(resourceId).into(holder.pizzaImage);
    }

    void removeItem(Pizza pizza) {
        int position = pizzas.indexOf(pizza);
        pizzas.remove(position);
        notifyItemRemoved(position);
    }

    void addItem(Pizza pizza) {
        pizzas.add(0, pizza);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    class PizzaViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaName;
        TextView pizzaDescription;
        ImageView pizzaImage;
        ImageButton closeCard;

        PizzaViewHolder(View itemView) {
            super(itemView);
            pizzaName = (TextView)itemView.findViewById(R.id.pizza_item_name);
            pizzaDescription = (TextView)itemView.findViewById(R.id.pizza_item_description);
            pizzaImage = (ImageView)itemView.findViewById(R.id.pizza_item_image);
            closeCard = (ImageButton)itemView.findViewById(R.id.remove_pizza_button);
            closeCard.setOnClickListener(listener);
        }
    }
}
