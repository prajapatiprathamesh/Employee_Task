package com.google.employee;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Assign_Task extends AppCompatActivity {
EditText tsk_name,tsk_due,username;
Button vw_tsk,ins_tsk,upd_tsk,del_tsk;
ImageButton buttonOpenCalendar,view_user;
DatabaseHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_assign_task);
        username =(EditText)findViewById(R.id.usrname);
        tsk_name=(EditText)findViewById(R.id.task_name);
        tsk_due =(EditText) findViewById(R.id.task_due);
        vw_tsk =(Button) findViewById(R.id.vw_tsk);
        ins_tsk=(Button) findViewById(R.id.ins_tsk);
        upd_tsk=(Button) findViewById(R.id.updt_tsk);
        del_tsk=(Button) findViewById(R.id.del_tsk);

      /*Image Button for calendar and user*/
        view_user =findViewById(R.id.vw_user);
        buttonOpenCalendar = findViewById(R.id.calendarButton);

        db = new DatabaseHelper(this);
        ins_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = username.getText().toString();
                String name = tsk_name.getText().toString();
                String due_date = tsk_due .getText().toString();
                if(usr.equals("")||name.equals("")||due_date.equals(""))
                {
                    Toast.makeText(Assign_Task.this, "Please enter the fields to assign a task", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean i= db.insert_task(usr,name,due_date);
                    if(i ==true){
                        Toast.makeText(Assign_Task.this,"Task Assigned Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Assign_Task.this,"Task Not Assigned",Toast.LENGTH_SHORT).show();
                    }
                }
                username.setText("");
                tsk_name.setText("");
                tsk_due.setText("");
            }
        });

        vw_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t =db.gettaskinfo();
                if(t.getCount()==0){
                    Toast.makeText(Assign_Task.this,"No Task Assign",Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer= new StringBuffer();
                while (t.moveToNext())
                {
                    buffer.append("Assign_to:"+t.getString(0)+"\n");
                    buffer.append("task_name: "+t.getString(1)+"\n");
                    buffer.append("Due_date: "+t.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Assign_Task.this);
                builder.setCancelable(true);
                builder.setTitle("Task Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        upd_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = username.getText().toString();
                String name = tsk_name.getText().toString();
                String due_date = tsk_due .getText().toString();
                Boolean i= db.update_task(usr,name,due_date);
                if(i ==true)
                    Toast.makeText(Assign_Task.this," Task Updated Successfully",Toast.LENGTH_SHORT).show();
                else

                    Toast.makeText(Assign_Task.this,"Task Not Updated",Toast.LENGTH_SHORT).show();
                username.setText("");
                tsk_name.setText("");
                tsk_due.setText("");
            }
        });

        del_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String due_date = tsk_due .getText().toString();
                Boolean i= db.delete_task(due_date);
                if(i ==true)
                    Toast.makeText(Assign_Task.this,"Task Deleted Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Assign_Task.this,"Task Not Deleted",Toast.LENGTH_SHORT).show();

                username.setText("");
                tsk_name.setText("");
                tsk_due.setText("");
            }
        });


        /* Source code for view user  */

        view_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t = db.getinfo();
                if (t.getCount() ==0)
                {
                    Toast.makeText(Assign_Task.this,"No Data Found",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(t.moveToNext())
                {
                    buffer.append("username:"+t.getString(0)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Assign_Task.this);
                builder.setCancelable(true);
                builder.setTitle("Users");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });


        /*source code for calendar*/
        buttonOpenCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatepickerDialog();
            }
        });
    }

    private void showDatepickerDialog() {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int dayOfMonth = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(Assign_Task.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update EditText with selected date
                tsk_due.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}