package com.google.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_Activity extends AppCompatActivity {
Button mang,tsk,view_tsk,upd,logout;
DatabaseHelper db;
     private static final long DOUBLE_BACK_PRESS_TIME = 2000; // 2 seconds/
     private long backPressedTime;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mang = (Button) findViewById(R.id.manag_emp);
        tsk =(Button) findViewById(R.id.task);
        view_tsk=(Button) findViewById(R.id.ViewTask);
        upd =(Button) findViewById(R.id.btnUpdate);
        logout=(Button) findViewById(R.id.btnLogout);
        db = new DatabaseHelper(this);
        mang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,Update_del_Activity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(Admin_Activity.this,MainActivity.class);
                startActivity(logout);
                Toast.makeText(Admin_Activity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            }
        });

        tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tsk = new Intent(Admin_Activity.this,Assign_Task.class);
                startActivity(tsk);
            }
        });

        view_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t =db.getsubmittaskinfo();
                if(t.getCount()==0){
                    Toast.makeText(Admin_Activity.this,"No Data Found",Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer= new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Submitted_by:"+t.getString(0)+"\n");
                    buffer.append("task_description:"+t.getString(1)+"\n");
                    buffer.append("Submission_progress:"+t.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Task Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msg = new Intent(getApplicationContext(),Message_Activity.class);
                startActivity(msg);
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