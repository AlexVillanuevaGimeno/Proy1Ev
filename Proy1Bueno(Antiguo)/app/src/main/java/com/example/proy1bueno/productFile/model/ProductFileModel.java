package com.example.proy1bueno.productFile.model;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.beans.Product;

import com.example.proy1bueno.listProductsUser.presenter.LstProductPresenter;
import com.example.proy1bueno.productFile.ContractProductFile;
import com.example.proy1bueno.productFile.presenter.ProductFilePresenter;

public class ProductFileModel extends AppCompatActivity implements ContractProductFile.Model{
    private SharedPreferences sharedPreferencesUserCFG;
    private Context context;

    private static final String IP_BASE = "192.168.1.132:8088";
    private ProductFilePresenter presenter;

    public ProductFileModel(ProductFilePresenter presenter, Context context){
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void productFileAPI(Product product, OnProductFileListener onProductFileListenerListener) {

    }
}
