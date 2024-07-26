package com.google.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mTextViewRegister;
    DatabaseHelper db;
    private static final long DOUBLE_BACK_PRESS_TIME = 2000; // 2 seconds
    private long backPressedTime;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        mTextUsername = (EditText) findViewById(R.id.username1);
        mTextPassword = (EditText) findViewById(R.id.password1);
        mButtonLogin = (Button) findViewById(R.id.log);
        mTextViewRegister = (Button) findViewById(R.id.reg);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                        if (mTextUsername.getText().toString().equals("Prathamesh@admin") && mTextPassword.getText().toString().equals("Prathamesh@1234")) {
                            Intent Admin = new Intent(MainActivity.this,Admin_Activity.class);
                            startActivity(Admin);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                        }
                    }
                mTextUsername.setText("");
                mTextPassword.setText("");
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

