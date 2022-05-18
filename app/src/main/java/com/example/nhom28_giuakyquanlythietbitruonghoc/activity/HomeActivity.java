package com.example.nhom28_giuakyquanlythietbitruonghoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom28_giuakyquanlythietbitruonghoc.R;
import com.google.firebase.auth.FirebaseAuth;
import com.example.nhom28_giuakyquanlythietbitruonghoc.MainActivity;

public class HomeActivity extends AppCompatActivity {


    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){

            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public void login(View view) {
        startActivity( new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity( new Intent(HomeActivity.this, RegistrationActivity.class));

    }
}