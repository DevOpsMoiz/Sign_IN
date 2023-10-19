package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Lanucher extends AppCompatActivity {
    FirebaseAuth Auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanucher);
        Auth = FirebaseAuth.getInstance(); //initialization
        user = Auth.getCurrentUser();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    Intent o = new Intent(Lanucher.this, HomePage.class);
                    startActivity(o);
                    finish();
                } else {
                    Intent r = new Intent(Lanucher.this, Login.class);
                    startActivity(r);
                    finish();                }
            }
        }, 2000);
    }
}