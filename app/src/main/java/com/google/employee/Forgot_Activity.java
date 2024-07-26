package com.google.employee;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Forgot_Activity extends AppCompatActivity {
    EditText usr,pass,repass;
    Button forgot;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
       db = new DatabaseHelper( this);
        usr = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repass);
        forgot=(Button) findViewById(R.id.Update);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usr.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

                if (username.equals("") || password.equals("") || repassword.equals("")){
                    Toast.makeText(Forgot_Activity.this, "Please enter the above fields", Toast.LENGTH_SHORT).show();
                }

                else if (password.length() <= 7) {
                    Toast.makeText(Forgot_Activity.this, "Password must be at least 8 Character", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.equals(repassword)) {
                        Boolean i = db.updatepass(username, password);
                        if (i == true)
                            Toast.makeText(Forgot_Activity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Forgot_Activity.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Forgot_Activity.this, "Password not matching", Toast.LENGTH_SHORT).show();

                    }
                }
                usr.setText("");
                pass.setText("");
                repass.setText("");
            }
        });
    }
}