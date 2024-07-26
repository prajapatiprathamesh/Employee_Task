package com.google.employee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Update_user extends AppCompatActivity {
    Button upd;
    MySecondDb db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        db = new MySecondDb(this);
        upd = (Button) findViewById(R.id.upd_msg);

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t = db.getmsginfo();
                if (t.getCount() == 0) {
                    Toast.makeText(Update_user.this, "No updates", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (t.moveToNext()) {
                    buffer.append("Message:"+t.getString(0)+"\n");
                    buffer.append("Date :" + t.getString(1) + "\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Update_user.this);
                builder.setCancelable(true);
                builder.setTitle(" Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}