package com.example.proy1bueno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proy1bueno.categoriesFilter.view.Categories;
import com.example.proy1bueno.loginUser.view.LoginUser;
import com.example.proy1bueno.lstBetterRates.view.LstBetterRates;
import com.example.proy1bueno.userFilter.view.UserFilter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIndex = findViewById(R.id.btnIndex);
        Button btnUserSells = findViewById(R.id.btnUserSells);
        Button btnBetterRateProducts = findViewById(R.id.btnBetterRateProducts);
        Button btnProductCategory = findViewById(R.id.btnProductsCategory);
        btnIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Código que se ejecutará al hacer clic    en el botón
                abrirLoginActivity();
            }
        });

        btnBetterRateProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirValoraciones();
            }
        });


        btnUserSells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirUsuarioVentas();
            }
        });

        btnProductCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirProductsCategory();
            }
        });
    }




    private void abrirLoginActivity() {
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }

    private void abrirValoraciones(){
        Intent intent = new Intent(this, LstBetterRates.class);
        startActivity(intent);
    }

    private void abrirUsuarioVentas(){
        Intent intent = new Intent(this, UserFilter.class);
        startActivity(intent);
    }

    private void abrirProductsCategory(){
        Intent intent = new Intent(this, Categories.class);
        startActivity(intent);
    }


}