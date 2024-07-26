package com.google.employee;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Complete_Task_Activity extends AppCompatActivity {
    DatabaseHelper db;
    EditText task_name,username;
    Button assign_tsk, sbt_task,del_tsk;
    private SeekBar progressSeekBar;
    private TextView progressTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task);


        db = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.usrname);
        task_name = (EditText) findViewById(R.id.tsk_name);
        sbt_task = (Button) findViewById(R.id.Submit_task);
        assign_tsk = (Button) findViewById(R.id.assign_task);
        del_tsk = (Button) findViewById(R.id.del_task);
        progressSeekBar = findViewById(R.id.progressSeekBar);
        progressTextView = findViewById(R.id.progressTextView);


        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressTextView.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        assign_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t = db.gettaskinfo();
                if (t.getCount() == 0) {
                    Toast.makeText(Complete_Task_Activity.this, "No Task Assign", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while (t.moveToNext()) {
                    buffer.append("Assign_to:" + t.getString(0)+"\n" );
                    buffer.append("Task_Descrption :" + t.getString(1) + "\n");
                    buffer.append("Due_date: " + t.getString(2) + "\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Complete_Task_Activity.this);
                builder.setCancelable(true);
                builder.setTitle("Task Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        sbt_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = username.getText().toString();
                String name = task_name.getText().toString();
                int tsk_progress = progressSeekBar.getProgress();
                if (usr.equals("") || name.equals("") || tsk_progress ==(0)) {
                    Toast.makeText(Complete_Task_Activity.this, "Please enter the fields to submit a task progress", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean i = db.submit_tsk(usr, name, tsk_progress);
                    if (i == true) {
                        Toast.makeText(Complete_Task_Activity.this, "Task Submitted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Complete_Task_Activity.this, "Task Not Submitted", Toast.LENGTH_SHORT).show();
                    }
                }
                task_name.setText("");
                progressSeekBar.setProgress(0);
                progressTextView.setText("0%");

            }
        });

        del_tsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr = username .getText().toString();
                Boolean i= db.delete_submit(usr);
                if(i ==true)
                    Toast.makeText(Complete_Task_Activity.this,"Task Deleted Successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Complete_Task_Activity.this,"Task Not Deleted",Toast.LENGTH_SHORT).show();
                username.setText("");
            }
        });
    }
    }
