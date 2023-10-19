package com.example.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText edt1, edt2;
    Button bdt1;
    TextView tv1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt1 = findViewById(R.id.Email);
        edt2 = findViewById(R.id.Pass);
        bdt1 = findViewById(R.id.LB);
        tv1 = findViewById(R.id.CreateAccountButton);
        Log.d("LoginActivity", "onCreate called");
        mAuth = FirebaseAuth.getInstance();

        CheckBox checkboxPrivacyPolicy = findViewById(R.id.privacyPolicyCheckbox);

        // Check if the checkbox is checked
        checkboxPrivacyPolicy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state, e.g., enable or disable the login button.
            }
        });


        checkboxPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the "Policy Activity" when the checkbox is clicked.
                Intent q = new Intent(Login.this, LoginWeb.class);
                startActivity(q);
            }
        });


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Login.this, MainActivity.class);
                startActivity(s);
                finish();
            }

        });
        bdt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt1.getText().toString().trim();
                String Password = edt2.getText().toString().trim();
                String uppercasePattern = ".*[A-Z].*";     // At least one uppercase letter
                String numberPattern = ".*\\d.*";          // At least one digit
                String symbolPattern = ".*[^A-Za-z0-9].*"; // At least one symbol (non-alphanumeric)

                if (username.isEmpty()) {
                    edt1.setError("Required");
                    return;
                }
                if (Password.isEmpty() || Password.length() < 8 || !Password.matches(uppercasePattern) || !Password.matches(numberPattern) || !Password.matches(symbolPattern)) {
                    edt2.setError("Enter Your Password (Contain 8 digits)[A-Z.a-z,0-9,%]");
                    return;

                }
                if (!checkboxPrivacyPolicy.isChecked())              //Check box
                {
                    checkboxPrivacyPolicy.setError("Check This");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    edt1.setError("Invalid Format");
                    return;
                }
                mAuth.signInWithEmailAndPassword(username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent p = new Intent(Login.this, HomePage.class);
                            startActivity(p);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}