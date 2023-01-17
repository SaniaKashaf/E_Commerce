package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.ecommerce.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categories  =new ArrayList<>();
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#4db151","Desciption",1));
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#b91147","Desciption",1));
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#4db151","Desciption",1));
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#1562ea","Desciption",1));
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#ea6b17","Desciption",1));
        categories.add(new Category("Sports & Outdoors","https://tutorials.mianasad.com/ecommerce/uploads/category/1673836769474.png","#4db151","Desciption",1));
        categoryAdapter=new CategoryAdapter(this,categories);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        binding.categoriesList.setLayoutManager(gridLayoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);

   }
}