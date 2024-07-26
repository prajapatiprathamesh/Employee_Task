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

public class Message_Activity extends AppCompatActivity {
   MySecondDb db;
    EditText e1,e2;
    Button b1,b2,b3;
    ImageButton buttonOpenCalendar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
     db = new MySecondDb(this);
     e1 =(EditText) findViewById(R.id.Msg);
     e2= (EditText) findViewById(R.id.Date);
     b1 =(Button) findViewById(R.id.view);
     b2=(Button) findViewById(R.id.btnInsert);
     b3=(Button) findViewById(R.id.btnDelete);
     buttonOpenCalendar = findViewById(R.id.calendar);

     b2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String message = e1.getText().toString();
             String date =e2.getText().toString();
             if(message.equals("")||date.equals(""))
             {
                 Toast.makeText(Message_Activity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
             }
            else{
                Boolean i = db.ins_msg(message,date);
                 {
                     if(i==true)
                     {
                         Toast.makeText(Message_Activity.this, "Successfully updated the message", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         Toast.makeText(Message_Activity.this, "Not Successful", Toast.LENGTH_SHORT).show();
                     }
                 }
             }
            e1.setText("");
            e2.setText("");
         }
     });
     b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Cursor t = db.getmsginfo();
             if (t.getCount() == 0) {
                 Toast.makeText(Message_Activity.this, "No Previous Messages", Toast.LENGTH_SHORT).show();
             }
             StringBuffer buffer = new StringBuffer();
             while (t.moveToNext()) {
                 buffer.append("Message:"+t.getString(0)+"\n");
                 buffer.append("Date :" + t.getString(1) + "\n");

             }
             AlertDialog.Builder builder = new AlertDialog.Builder(Message_Activity.this);
             builder.setCancelable(true);
             builder.setTitle(" Data");
             builder.setMessage(buffer.toString());
             builder.show();
         }
     });

     b3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String date = e2.getText().toString();
             Boolean i= db.delete_msg(date);
             if(i ==true)
                 Toast.makeText(Message_Activity.this,"Previous Messages Deleted",Toast.LENGTH_SHORT).show();
             else
                 Toast.makeText(Message_Activity.this,"Not Deleted",Toast.LENGTH_SHORT).show();
             e1.setText("");
             e2.setText("");

         }
     });

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
        DatePickerDialog datePickerDialog = new DatePickerDialog(Message_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Update EditText with selected date
                e2.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}