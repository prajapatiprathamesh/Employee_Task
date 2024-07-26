package com.google.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private static final long DOUBLE_BACK_PRESS_TIME = 2000; // 2 seconds
    private long backPressedTime;
    EditText username,password;
    Button btnlogin;
    Button btnregister,btnforg;
    DatabaseHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button)  findViewById(R.id.btsignin);
        btnregister=(Button) findViewById(R.id.btnreg);
        btnforg=(Button)findViewById(R.id.btnforgot);

        DB = new DatabaseHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();}

                if(pass.length() <=7){
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass == true){
                        Toast.makeText(LoginActivity.this,"Login Successful!!!!!!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Navigation_Activity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
                username.setText("");
                password.setText("");

            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetnt =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intetnt);

            }
        });

        btnforg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(LoginActivity.this,Forgot_Activity.class);
                startActivity(forgot);
            }
        });

    }
    public void onBackPressed(){
        if (backPressedTime + DOUBLE_BACK_PRESS_TIME > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    }

