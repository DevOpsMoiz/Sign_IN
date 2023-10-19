package com.example.signin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4;
    Button bt1;
    TextView tv1;
    FirebaseAuth mAuth;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String CheckBox2 = "checkbox2";
    public static final String Name1 = "name1";
    public static final String Email1 = "email1";
    public static final String Password1 = "password1";
    public static final String Cpassword1 = "cpassword1";

    private String Name2;
    private String Email2;
    private String Password3;
    private String Cpassword3;
    private boolean CheckBox3;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.Name);
        et2 = findViewById(R.id.mail);
        et3 = findViewById(R.id.Password);
        et4 = findViewById(R.id.Cpassword);
        bt1 = findViewById(R.id.Sign_Up);
        tv1 = findViewById(R.id.Create_New_Account);
        Log.d("SignUpActivity", "onCreate called");

        mAuth = FirebaseAuth.getInstance();
        CheckBox checkbox1 = findViewById(R.id.Checkbox);

//        if (!checkbox1.isChecked()) {
//            checkbox1.setError("Check This");
//            return;
//        }


        // Check if the checkbox is checked
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the checkbox state, e.g., enable or disable the login button.
            }
        });

        checkbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the "Policy Activity" when the checkbox is clicked.
                Intent f = new Intent(MainActivity.this, SignUpWeb.class);
                startActivity(f);

//                SaveData();
            }

//            private void SaveData() {
//                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(Name1, et1.getText().toString());
//                editor.putBoolean(CheckBox2, checkbox1.isChecked());
//                editor.putString(Email1, et2.getText().toString());
//                editor.putString(Password1, et3.getText().toString());
//                editor.putString(Cpassword1, et4.getText().toString());
//
//            }
        });


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(MainActivity.this, Login.class);
                startActivity(s);
                finish();
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = et1.getText().toString().trim();
                String Email = et2.getText().toString().trim();
                String Pass = et3.getText().toString().trim();
                String Cpass = et4.getText().toString().trim();

                if (!Pass.equals(Cpass)) {
                    et3.setError("Passwords don't match");
                    et4.setError("Passwords don't match");
                    return;
                }

                if (!checkbox1.isChecked())              //Check box
                {
                    checkbox1.setError("Check This");
                    return;
                }
                if (Name.isEmpty()) {
                    et1.setError("Enter Your Name");
                    return;

                }
                if (Email.isEmpty()) {
                    et2.setError("Enter Your Password");
                    return;
                }

                if (Pass.isEmpty() || Pass.length() < 8) {
                    et3.setError("Enter Your Unique Password");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    et2.setError("Invalid Format");
                    return;
                }
                if (Name.isEmpty()) {
                    et1.setError("Enter Confirm Password");
                    return;

                }

                mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Registration was successful
                            Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Navigate to the home page or any desired activity
                            Intent u = new Intent(MainActivity.this, HomePage.class);
                            startActivity(u);
                            finish();
                        } else {
                            // Registration failed
                            Toast.makeText(MainActivity.this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

//        LoadData();
//        updateView();

    }

//    private void updateView() {
//        et1.setText(Name2);
//        et2.setText(Email2);
//        et3.setText(Password3);
//        et4.setText(Cpassword3);
//
//    }

//    private void LoadData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        Name2 = sharedPreferences.getString(Name1, "");
//        Email2 = sharedPreferences.getString(Email1, "");
//        Password3 = sharedPreferences.getString(Password1, "");
//        Cpassword3 = sharedPreferences.getString(Cpassword1, "");
//        CheckBox3 = sharedPreferences.getBoolean(CheckBox2, false);
//
//
//    }
}