package com.google.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    DatabaseHelper DB;
    private static final long DOUBLE_BACK_PRESS_TIME = 2000; // 2 seconds
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        repassword = (EditText) findViewById(R.id.cpass);
        signup = (Button) findViewById(R.id.btnsignup);



        DB = new DatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();



                if (user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please enter the above fields to register", Toast.LENGTH_SHORT).show();
                }

                else if (pass.length() <= 7) {
                        Toast.makeText(RegisterActivity.this, "Password must be at least 8 Character", Toast.LENGTH_SHORT).show();
                    }

                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Registered Successful!!!!", Toast.LENGTH_SHORT).show();
                                Intent usr = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(usr);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Already Exist! please sign in ", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();

                    }

                }
                username.setText("");
                password.setText("");
                repassword.setText("");
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
