package com.example.proy1bueno.listProductsUser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proy1bueno.MainActivity;
import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.ProductAdapter;
import com.example.proy1bueno.adapters.ProductPerUserAdapter;
import com.example.proy1bueno.addProduct.view.AddProduct;
import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.listProductsUser.ContractLstProduct;
import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.loginUser.view.LoginUser;
import com.example.proy1bueno.rate.view.Rate;

import java.util.ArrayList;

public class LstProducts extends AppCompatActivity implements ContractLstProduct.View{
    Button btnLogout;
    Button btnAddProduct;
    Button btnRate;
    RecyclerView recyclerView;
    private LstProductPresenter presenter = new LstProductPresenter(this);

    private static LstProducts lstProducts = null;

    private static LstProducts getInstance(){
        if (lstProducts == null){
            lstProducts = new LstProducts();
        }
        return lstProducts;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_products);
        lstProducts = this;
        initComponents();
    }

    private void initComponents() {
        SharedPreferences UserPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        Log.e("user_id","id user preferences = " + UserPreferences);
        Product product = new Product();
        presenter.lstProducts(product);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnLogout = findViewById(R.id.btnLogout);
//        click en Add Products
        btnAddProduct.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddProduct.class);
            startActivity(intent);
        });
        //Click en Logout
        btnLogout.setOnClickListener(view -> {
            SharedPreferences.Editor editor = UserPreferences.edit();
            editor.remove("LogCheck");
            editor.remove("username");
            editor.remove("idUser");
            Log.e("Prefernces delete","borro credenciales" );
            editor.apply();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

//    public int dpToPx(int dp) {
//        float density = getResources().getDisplayMetrics().density;
//        return Math.round((float) dp * density);
//    }

    @Override
    public void successLstProduct(ArrayList<Product> lstProducts) {

        recyclerView = findViewById(R.id.columnaListado);
        ProductPerUserAdapter productPerUserAdapter = new ProductPerUserAdapter(lstProducts);
        recyclerView.setAdapter(productPerUserAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

//        LinearLayout columnaListado = findViewById(R.id.columnaListado);
//        LinearLayout parentEl = null;
//
//        for (Product product : lstProducts) {
//            parentEl = columnaListado;
//            Log.e("SuccesList", "ProductForEach" + product.toString());
//
//            // Crear una CardView para cada producto
//            CardView cardView = createProductCardView(product);
//            // Agregar la CardView al contenedor principal
//            parentEl.addView(cardView);
//
//            Log.e("listaPRODUCTOS", "LISTADO: " + product.getNombreProducto() + " " + product.getMarcaProducto() + " " + product.getPrecioProducto() + "€");
//        }
//    }


// Método para crear la CardView de un producto
//        private CardView createProductCardView(Product product) {
//            CardView cardView = new CardView(this);
//            CardView.LayoutParams cardParams = new CardView.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            cardParams.setMargins(0, dpToPx(5), 0, 0);
//            cardView.setLayoutParams(cardParams);
//
//            // Establecer el color de fondo de la CardView
//            cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.my_primary));
//
//            // Crear un LinearLayout dentro de la CardView
//            LinearLayout linearLayout = new LinearLayout(this);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            );
//            linearLayout.setLayoutParams(layoutParams);
//            linearLayout.setOrientation(LinearLayout.HORIZONTAL); // Cambiado a horizontal
//            linearLayout.setGravity(Gravity.CENTER); // Añadido gravity center al LinearLayout
//
//            // ImageView para la imagen del producto
//            ImageView imageView = createProductImageView();
//            // TextView para mostrar información del producto
//            TextView textView = createProductTextView(product);
//            // Button para calificar
//            Button btnRate = createRateButton(product);
//
//            // Agregar ImageView, TextView y Button al LinearLayout
//            linearLayout.addView(imageView);
//            linearLayout.addView(textView);
//            linearLayout.addView(btnRate);
//
//            // Agregar el LinearLayout a la CardView
//            cardView.addView(linearLayout);
//
//            return cardView;
//        }
//
//// Métodos de utilidad para crear componentes
//
//        private ImageView createProductImageView() {
//            ImageView imageView = new ImageView(this);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                    dpToPx(60), // Ancho de la imagen
//                    dpToPx(60)  // Altura de la imagen
//            ));
//            // Aquí debes cargar la imagen del producto en imageView, por ejemplo:
//            imageView.setImageResource(R.drawable.cancel_icon);
//            imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
//            imageView.setForegroundGravity(Gravity.CENTER);
//            return imageView;
//        }
//
//        private TextView createProductTextView(Product product) {
//            TextView textView = new TextView(this);
//            textView.setLayoutParams(new LinearLayout.LayoutParams(
//                    0,
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    1  // Peso 1 para ocupar espacio restante
//            ));
//            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER); // Añadido gravity center al TextView
//            textView.setTextColor(ContextCompat.getColor(this, R.color.black));
//            textView.setTextAppearance(android.R.style.TextAppearance_Medium); // Aumentar el tamaño del texto
//            textView.setTypeface(textView.getTypeface(), Typeface.BOLD); // Poner el texto en negrita
//            textView.setText(product.getNombreProducto() + " " + product.getMarcaProducto() + " " + product.getPrecioProducto() + "€");
//            return textView;
//        }
//
//        private Button createRateButton(Product product) {
//            Button btnRate = new Button(this);
//            btnRate.setLayoutParams(new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
//            btnRate.setText("Rate");
//            btnRate.setGravity(Gravity.CENTER); // Añadido gravity center al Button
//            btnRate.setOnClickListener(v -> {
//                Intent intent = new Intent(LstProducts.this, Rate.class);
//                intent.putExtra("idProducto", product.getIdProducto());
//                startActivity(intent);
//            });
//            return btnRate;
//        }








        @Override
    public void failureLstProduct(String err) {

    }
}