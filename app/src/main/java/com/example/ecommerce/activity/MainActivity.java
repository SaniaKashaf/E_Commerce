package com.example.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommerce.R;
import com.example.ecommerce.adapterClass.CategoryAdapter;
import com.example.ecommerce.adapterClass.productAdapter;
import com.example.ecommerce.databinding.ActivityMainBinding;
import com.example.ecommerce.modelClass.Category;
import com.example.ecommerce.modelClass.Product;
import com.example.ecommerce.utils.Constants;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;


    productAdapter productAdapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);

                intent.putExtra("query",text.toString());
                startActivity(intent);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

initCatagories();
initProducts();
initSlider();


   }

    private void initSlider() {

       getRecentOffers();

    }

    void initCatagories(){
       categories  =new ArrayList<>();


       categoryAdapter=new CategoryAdapter(this,categories);

        getCategories();


       GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
       binding.categoriesList.setLayoutManager(gridLayoutManager);
       binding.categoriesList.setAdapter(categoryAdapter);
   }


   void getCategories(){

       RequestQueue queue = Volley.newRequestQueue(this);
       StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               try {
                   JSONObject mainobj=new JSONObject(response);
                   if (mainobj.getString("status").equals("success")){
                       JSONArray catagoriesArray=mainobj.getJSONArray("categories");
for (int i=0;i<catagoriesArray.length();i++) {
    JSONObject object = catagoriesArray.getJSONObject(i);
    Category category = new Category(

            object.getString("name"),
         Constants.CATEGORIES_IMAGE_URL +   object.getString("icon"),
            object.getString("color"),
            object.getString("brief"),
            object.getInt("id")
    );
    categories.add(category);
}
                   categoryAdapter.notifyDataSetChanged();

                   }else {

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
       queue.add(request);
   }
    void getRecentProducts(){
RequestQueue queue=Volley.newRequestQueue(this);
String url=Constants.GET_PRODUCTS_URL+"?count=8";
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
queue.add(request);


    }
    void getRecentOffers(){
        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
if (jsonObject.getString("status").equals("success")){
    JSONArray offerArray=jsonObject.getJSONArray("news_infos");
    for (int i=0;i<offerArray.length();i++){
        JSONObject childObj=offerArray.getJSONObject(i);
        binding.carousel.addData(
                new CarouselItem(
                     Constants.NEWS_IMAGE_URL+   childObj.getString("image"),
                        childObj.getString("title")
                )
        );
    }
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
        queue.add(request);
    }

   void initProducts(){
        products= new ArrayList<>();


        productAdapter=new productAdapter(this,products);

        getRecentProducts();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(gridLayoutManager);
        binding.productList.setAdapter(productAdapter);

   }
}