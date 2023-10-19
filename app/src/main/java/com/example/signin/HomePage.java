package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    Button bb1;
    FirebaseAuth MAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bb1 = findViewById(R.id.Logout);
        MAuth = FirebaseAuth.getInstance();


        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MAuth.signOut();
                Intent i = new Intent(HomePage.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}