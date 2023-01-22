package com.example.ecommerce.adapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.databinding.ItemCartBinding;
import com.example.ecommerce.databinding.QuantityDialogBinding;
import com.example.ecommerce.modelClass.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

Context context;
ArrayList<Product> products;
CartListner cartListner;
    Cart cart;

public interface  CartListner{
    public void onQuantityChanged();

}

    public CartAdapter(Context context, ArrayList<Product> products,CartListner cartListner) {
        this.context = context;
        this.products = products;
        this.cartListner=cartListner;
        cart = TinyCartHelper.getCart();

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

Product product=products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

        holder.binding.name.setText(product.getName());
holder.binding.price.setText("PKR"+product.getPrice());
holder.binding.quantity.setText(product.getQuantity()+"item(s)");

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        QuantityDialogBinding quantityDialogBinding=QuantityDialogBinding.inflate(LayoutInflater.from(context));

        AlertDialog alertDialog=new AlertDialog.Builder(context)
                .setView(quantityDialogBinding.getRoot())
                .create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

        quantityDialogBinding.productName.setText(product.getName());

        quantityDialogBinding.productStock.setText("Stock:"+product.getStock());
        quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));

        int stock=product.getStock();

        quantityDialogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantity=product.getQuantity();

                quantity++;

                if (quantity>product.getStock()){
                    Toast.makeText(context, "Max Stock Available", Toast.LENGTH_SHORT).show();
                return;
                }else {
                    product.setQuantity(quantity);
                    quantityDialogBinding.quantity.setText(String.valueOf(quantity));

                }
                notifyDataSetChanged();
                cart.updateItem(product,product.getQuantity());
                cartListner.onQuantityChanged();

            }
        });
        quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

int quantity=product.getQuantity();
if (quantity>1)
    quantity--;
                product.setQuantity(quantity);

                quantityDialogBinding.quantity.setText(String.valueOf(quantity));
                notifyDataSetChanged();
                cart.updateItem(product,product.getQuantity());
                cartListner.onQuantityChanged();

            }
        });
  quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
alertDialog.dismiss();
/*
notifyDataSetChanged();
cart.updateItem(product,product.getQuantity());
cartListner.onQuantityChanged();
*/

            }
        });



        alertDialog.show();
    }
});



    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
ItemCartBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

binding=ItemCartBinding.bind(itemView);


        }
    }
}
