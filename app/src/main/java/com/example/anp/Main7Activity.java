package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main7Activity extends AppCompatActivity {

    LinearLayout accepted,unaccepted,registration,logout,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        Intent i=getIntent();
       final String username=i.getStringExtra("username");
        accepted=findViewById(R.id.accepted_layout);
        unaccepted=findViewById(R.id.not_accepted_layout);
        registration=findViewById(R.id.visitor_registration_layout);
        logout=findViewById(R.id.logout_layout);
        exit=findViewById(R.id.exit_layout);
        TextView t=findViewById(R.id.guard_unknown);
        t.setText("Guard : "+username);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(" Welcome, "+username);
        builder.setMessage("Press Okay to Continue..");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Welcome..",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),GUARD_ACCEPTED.class);
                i.putExtra("username",username);
                i.putExtra("callfor","accepted");
                startActivity(i);
            }
        });
        unaccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),GUARD_ACCEPTED.class);
                i.putExtra("username",username);
                i.putExtra("callfor","unaccepted");
                startActivity(i);
            }
        });
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(getApplicationContext(),Visitor_registartion.class);
                k.putExtra("username",username);
                startActivity(k);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database d=new database(Main7Activity.this);
                d.Delete(username,"guard");
                stopService(new Intent(getApplicationContext(),background_service.class));
                startService(new Intent(getApplicationContext(),background_service.class));
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),HAS_MEET.class);
                i.putExtra("username",username);
                i.putExtra("callfor","guard");
                startActivity(i);
            }
        });
    }
}
