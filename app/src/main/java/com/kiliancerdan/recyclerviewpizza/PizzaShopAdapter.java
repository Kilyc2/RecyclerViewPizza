package com.kiliancerdan.recyclerviewpizza;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiliancerdan.pizzashop.model.Pizza;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PizzaShopAdapter extends RecyclerView.Adapter<PizzaShopAdapter.ViewHolder>
        implements View.OnClickListener {

    List<Pizza> menu;

    public PizzaShopAdapter(List<Pizza> menu) {
        this.menu = menu;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_item, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pizza pizza = menu.get(position);
        holder.itemView.setTag(pizza);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaDescription.setText(pizza.getDescription());
        setImage(holder, pizza.getImage());
    }

    private void setImage(ViewHolder holder, String pizza) {
        Context context = holder.itemView.getContext();
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(pizza, "drawable",
                context.getPackageName());
        Picasso.with(context).load(resourceId).into(holder.pizzaImage);
    }

    @Override
    public void onClick(View v) {
        Pizza pizza = (Pizza)v.getTag();
        int position = menu.indexOf(pizza);
        removeItem(position);
    }

    public void removeItem(int position) {
        menu.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(Pizza pizza) {
        menu.add(0, pizza);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView pizzaName;
        TextView pizzaDescription;
        ImageView pizzaImage;

        public ViewHolder(View itemView) {
            super(itemView);
            pizzaName = (TextView)itemView.findViewById(R.id.pizza_item_name);
            pizzaDescription = (TextView)itemView.findViewById(R.id.pizza_item_description);
            pizzaImage = (ImageView)itemView.findViewById(R.id.pizza_item_image);
        }
    }
}
