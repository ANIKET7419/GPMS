package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    LinearLayout requests,logout,hasmeet;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Intent i=getIntent();
       final String username=i.getStringExtra("username");
       AlertDialog.Builder builder=new AlertDialog.Builder(this);
       builder.setMessage("Hello, "+username);
       builder.setTitle("Welcome..");
       builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
       builder.show();
       t=findViewById(R.id.teacher_unknown);
       t.setText(" Welcome, "+username);
        requests=findViewById(R.id.teacher_requests);
        logout=findViewById(R.id.teacher_logout);
        hasmeet=findViewById(R.id.teacher_hasmeet);
        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),Visitor_request_to_teacher.class);
                i.putExtra("username",username);
                i.putExtra("callfor","teacher");
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database d=new database(Main6Activity.this);
                d.Delete(username,"teacher");
                stopService(new Intent(getApplicationContext(),background_service.class));
                startService(new Intent(getApplicationContext(),background_service.class));
                finish();
            }
        });
        hasmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),HAS_MEET.class);
                i.putExtra("callfor","teacher");
                i.putExtra("username",username);
                startActivity(i);
            }
        });


    }
}
