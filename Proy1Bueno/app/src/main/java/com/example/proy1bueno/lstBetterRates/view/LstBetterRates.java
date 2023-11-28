package com.example.proy1bueno.lstBetterRates.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.proy1bueno.MainActivity;
import com.example.proy1bueno.R;
import com.example.proy1bueno.adapters.ProductAdapter;
import com.example.proy1bueno.adapters.ProductRatedAdapter;
import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.beans.Valoracion;
import com.example.proy1bueno.lstBetterRates.ContractLstBetterRates;
import com.example.proy1bueno.lstBetterRates.presenter.LstBetterRatesPresenter;



import java.util.ArrayList;

public class LstBetterRates extends AppCompatActivity implements ContractLstBetterRates.View{
    private ImageButton btnHome;
    RecyclerView recyclerView;



    private LstBetterRatesPresenter presenter = new LstBetterRatesPresenter(this);
    private static LstBetterRates lstBetterRates = null;

    private static LstBetterRates getInstance(){
        if (lstBetterRates == null){
            lstBetterRates = new LstBetterRates();
        }
        return lstBetterRates;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_better_rates);
        lstBetterRates = this;
        initComponents();
    }

    private void initComponents() {
        Valoracion valoracion = new Valoracion();
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        presenter.lstBetterRates(valoracion);
    }

    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public void succesLstBetterRates(ArrayList<Valoracion> lstValoraciones) {
        recyclerView = findViewById(R.id.productsColumn);
        ProductRatedAdapter productRatedAdapter = new ProductRatedAdapter(lstValoraciones);
        recyclerView.setAdapter(productRatedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void failureLstBetterRates(String err) {

    }
}