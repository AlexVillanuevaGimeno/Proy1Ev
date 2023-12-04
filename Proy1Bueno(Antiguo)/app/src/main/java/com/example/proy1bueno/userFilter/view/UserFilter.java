package com.example.proy1bueno.userFilter.view;

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

import com.example.proy1bueno.adapters.PopularUsersAdapter;
import com.example.proy1bueno.adapters.ProductAdapter;
import com.example.proy1bueno.beans.User;
import com.example.proy1bueno.userFilter.ContractUserFilter;
import com.example.proy1bueno.userFilter.presenter.UserFilterPresenter;

import java.util.ArrayList;

public class UserFilter extends AppCompatActivity implements ContractUserFilter.View{
    private ImageButton btnHome;
    RecyclerView recyclerView;


    private UserFilterPresenter presenter = new UserFilterPresenter(this);
    private static UserFilter userFilter = null;

    private static UserFilter getInstance(){
        if (userFilter == null){
            userFilter = new UserFilter();
        }
        return userFilter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_filter);
        userFilter = this;
        initComponents();

    }

    private void initComponents() {
        User user = new User();
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        presenter.userFilter(user);
    }

//    public int dpToPx(int dp) {
//        float density = getResources().getDisplayMetrics().density;
//        return Math.round((float) dp * density);
//    }





    @Override
    public void successUserFilter(ArrayList<User> usersList) {
        recyclerView = findViewById(R.id.usersColumn);
        PopularUsersAdapter adapterUsers = new PopularUsersAdapter(usersList);
        recyclerView.setAdapter(adapterUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void failureUserFilter(String err) {

    }
}