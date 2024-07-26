package com.google.employee;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Update_del_Activity extends AppCompatActivity {
    EditText uname,pass;
    Button ins,upd,del,viw;
    DatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_del);
        uname = (EditText) findViewById(R.id.username1);
        pass = (EditText) findViewById(R.id.password1);
        ins = (Button) findViewById(R.id.ins);
        upd = (Button) findViewById(R.id.updt);
        del = (Button) findViewById(R.id.del);
        viw = (Button) findViewById(R.id.view);
        db = new DatabaseHelper(this);

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname = uname.getText().toString();
                String pwd = pass.getText().toString();
                if (usrname.equals("") || pwd.equals("")) {
                    Toast.makeText(Update_del_Activity.this, "Enter all the Fields", Toast.LENGTH_SHORT).show();
                }
                if(pass.length() <=7){
                    Toast.makeText(Update_del_Activity.this, "Password must be atleast 8 Character", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean i = db.insert(usrname, pwd);
                    if (i == true) {
                        Toast.makeText(Update_del_Activity.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Update_del_Activity.this, "Data in Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                uname.setText("");
                pass.setText("");
            }
        });

        viw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t = db.getinfo();
                if (t.getCount() ==0)
                {
                    Toast.makeText(Update_del_Activity.this,"No Data Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(t.moveToNext())
                {
                    buffer.append("username:"+t.getString(0)+"\n");
                    buffer.append("password:"+t.getString(1)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_del_Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Employee Data");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= uname.getText().toString();
                String password = pass.getText().toString();
                Boolean i =db.update_data(username,password);
                if(i==true)
                    Toast.makeText(Update_del_Activity.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Update_del_Activity.this,"Data Not Updated",Toast.LENGTH_SHORT).show();

                uname.setText("");
                pass.setText("");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                Boolean i = db.delete_data(username);
                if(i == true)
                    Toast.makeText(Update_del_Activity.this,"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Update_del_Activity.this,"Data is not Deleted",Toast.LENGTH_SHORT).show();

                uname.setText("");
                pass.setText("");
            }
        });

    }

}