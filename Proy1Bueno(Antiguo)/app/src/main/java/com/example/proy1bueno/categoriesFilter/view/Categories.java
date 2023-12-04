package com.example.proy1bueno.categoriesFilter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proy1bueno.MainActivity;
import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.ProductAdapter;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.categoriesFilter.ContractCategoriesFilter;
import com.example.proy1bueno.categoriesFilter.presenter.CategoriesFilterPresenter;

import java.util.ArrayList;

public class Categories extends AppCompatActivity implements ContractCategoriesFilter.View{
    private SharedPreferences sharedPreferences;

    Button btnHombre;
    Button btnMujer;
    Button btnCamisetas;
    Button btnPantalones;
    ImageButton btnHome;

    //boton para limpriar los filtros a la vez
//    Button btnClear;

    //necesito:
    // Array de categorias y de productos
    //presenter
    //declaro la actividad = null
    private ArrayList<String>categories;
     ArrayList<Product>lstProducts;
    private CategoriesFilterPresenter presenter = new CategoriesFilterPresenter(this);
//    private static Categories categoriesActivity = null;

    //Uso RecyclerView
    //Paso losproductos al recycler
    private RecyclerView recyclerView;
    private ArrayList<Product> RecyclerLstProducts;

//    public static Categories getInstance() {
//        if (categoriesActivity == null){
//            categoriesActivity = new Categories();
//        }
//        return categoriesActivity;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
//        categoriesActivity=this;
        sharedPreferences = getSharedPreferences("com.MyApp.PRODUCTS",MODE_PRIVATE);
        initComponents();
    }

    private void initComponents(){
        //Arrays
        categories = new ArrayList<>();
        Product product = new Product();
        product.setCategory("");
        btnHombre = findViewById(R.id.btnHombre);
        btnMujer = findViewById(R.id.btnMujer);
        btnCamisetas = findViewById(R.id.btnCamisetas);
        btnPantalones = findViewById(R.id.btnPantalones);
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        //onClick compruebo si el filtro ya estaba añadido
        //en caso deque lo este al clicar otra vez lo elimino
        btnHombre.setOnClickListener(v -> {
            if (categories.contains("Hombre")){
                categories.remove("Hombre");
            }else{
                categories.add("Hombre");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnMujer.setOnClickListener(v -> {
            if (categories.contains("Mujer")){
                categories.remove("Mujer");
            }else{
                categories.add("Mujer");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnCamisetas.setOnClickListener(v -> {
            if (categories.contains("Camisetas")){
                categories.remove("Camisetas");
            }else{
                categories.add("Camisetas");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        btnPantalones.setOnClickListener(v -> {
            if (categories.contains("Pantalones")){
                categories.remove("Pantalones");
            }else{
                categories.add("Pantalones");
            }
            //seteo el filtro en los productos
            product.setLstCategories(categories);
            //llamo al presenter para que ejecute
            presenter.categoriesFilter(product);
        });
        //vuelvo a llamar al presenter para que ejecute los filtros en cualquier caso
        //(sea quitar filtros o ponerlos)
        presenter.categoriesFilter(product);
    }

    @Override
    public void succesCategoriesFilter(ArrayList<Product> lstProducts) {
        this.lstProducts = lstProducts;
        Log.e("SUCCES CATEGORIES FILTER","HE LLEGADO AL SUCCES DEL FILTRO CON ESTOS DATOS= " +
                lstProducts + "\n Longuitud de la cadena" + lstProducts.size());
        recyclerView = findViewById(R.id.columnaListado);
        ProductAdapter adapterProduct = new ProductAdapter(lstProducts);
        recyclerView.setAdapter(adapterProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void failureCategoriesFilter(String err) {

    }
}