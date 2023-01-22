package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommerce.adapterClass.productAdapter;
import com.example.ecommerce.databinding.ActivityCategoryBinding;
import com.example.ecommerce.modelClass.Product;
import com.example.ecommerce.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;

    productAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* products= new ArrayList<>();
        productAdapter=new productAdapter(this,products);

int catId=getIntent().getIntExtra("catID",0);
String categoryName=getIntent().getStringExtra("categoryName");

getSupportActionBar().setTitle(categoryName);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getProducts(catId);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(gridLayoutManager);
        binding.productList.setAdapter(productAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    void getProducts(int catId){
        RequestQueue queue=Volley.newRequestQueue(this);
        String url=Constants.GET_PRODUCTS_URL+"?category_id="+ catId;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getString("status").equals("success")){
                        JSONArray productArray=jsonObject.getJSONArray("products");
                        for (int i=0;i<productArray.length();i++){

                            JSONObject childObject=productArray.getJSONObject(i);
                            Product product=new Product(
                                    childObject.getString("name"),
                                    Constants.PRODUCTS_IMAGE_URL+  childObject.getString("image"),
                                    childObject.getString("status"),
                                    childObject.getDouble("price"),
                                    childObject.getDouble("price_discount"),
                                    childObject.getInt("stock"),
                                    childObject.getInt("id")

                            );
                            products.add(product);
                        }
                        productAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);*/
        products = new ArrayList<>();
        productAdapter = new productAdapter(this, products);


        int catId = getIntent().getIntExtra("catId", 0);
        String categoryName = getIntent().getStringExtra("categoryName");

        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getProducts(catId);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void getProducts(int catId) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = Constants.GET_PRODUCTS_URL + "?category_id=" + catId;
        StringRequest request = new StringRequest(Request.Method.GET, url,response ->  {
            try {
                JSONObject object = new JSONObject(response);
                if(object.getString("status").equals("success")){
                    JSONArray productsArray = object.getJSONArray("products");
                    for(int i =0; i< productsArray.length(); i++) {
                        JSONObject childObj = productsArray.getJSONObject(i);
                        Product product = new Product(
                                childObj.getString("name"),
                                Constants.PRODUCTS_IMAGE_URL + childObj.getString("image"),
                                childObj.getString("status"),
                                childObj.getDouble("price"),
                                childObj.getDouble("price_discount"),
                                childObj.getInt("stock"),
                                childObj.getInt("id")

                        );
                        products.add(product);
                    }
                    productAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);

    }
}