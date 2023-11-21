package com.example.proy1bueno.login_user.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proy1bueno.R;
import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.listProductsUser.view.LstProducts;
import com.example.proy1bueno.login_user.ContractLoginUser;
import com.example.proy1bueno.login_user.data.DataUser;
import com.example.proy1bueno.login_user.presenter.LoginUserPresenter;

public class LoginUser extends AppCompatActivity implements ContractLoginUser.View{
    SharedPreferences userPreferences;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;

    private LoginUserPresenter presenter =
            new LoginUserPresenter(this);

    /* PATRÃ“N SINGLETON*/
    private static LoginUser mainActivity = null;
    public static LoginUser getInstance(){
        return mainActivity; //0x457845AF
    }
    /* FIN PATRÃ“N SINGLETON*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        mainActivity = this;
        initComponents();

    }
    private boolean LogCheck(){
        return userPreferences.getBoolean("LogCheck", false);
    }
    private void initComponents(){
        userPreferences = getSharedPreferences("com.MyApp.USER_CFG", Context.MODE_PRIVATE);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            if ( !LogCheck() ){
                User user = new User();
                user.setUsername(edtEmail.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                presenter.login(user);
                Log.e("User To string","User: " + user);
                Log.e("logged success", "logged finished");
            }else{
                String datosUser = "";
                datosUser += "{Username: " + userPreferences.getString("username", "YO") + "},";
                datosUser += "idUser: " + userPreferences.getInt("idUser",0) + "}";
//                        Toast.makeText(this, DataUser, Toast.LENGTH_SHORT).show();
                Log.e("success", "LogCheck returned true");
                Intent intent = new Intent(this, LstProducts.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void successLogin(User user) {
        SharedPreferences.Editor editorPreferencias = userPreferences.edit();
        editorPreferencias.putBoolean("LogCheck", true);
        editorPreferencias.putString("username", user.getUsername());
        editorPreferencias.putInt("idUser", user.getIdUser());
        editorPreferencias.apply();
        Log.e("success", "editor añade user + id al preferences");
       Intent intent = new Intent(this, LstProducts.class);
               startActivity(intent);
    }

    @Override
    public void failureLogin(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

}