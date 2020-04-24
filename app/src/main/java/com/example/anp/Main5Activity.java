package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.sql.Statement;

public class Main5Activity extends AppCompatActivity {
    LinearLayout unaccepted;
    LinearLayout profile;
    LinearLayout teacherreg;
    LinearLayout guardreg;
    LinearLayout teacherdet;
    LinearLayout guarddet;
    LinearLayout history1;
    LinearLayout logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Intent i=getIntent();
       final String username=i.getStringExtra("username");
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(":::::Welcome:::::");
        dialog.setMessage("Hello, "+username+"\n Press Okay to Continue...");
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
        unaccepted=findViewById(R.id.unacceptedl);
        profile=findViewById(R.id.profilel);
        teacherreg=findViewById(R.id.teacherregl);
        guardreg=findViewById(R.id.guardregl);
        teacherdet=findViewById(R.id.teacherdetaill);
        guarddet=findViewById(R.id.guarddetaill);
        history1=findViewById(R.id.historyl);
        logout=findViewById(R.id.logoutl);
        unaccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),Visitor_request_to_teacher.class);
                i.putExtra("username",username);
                i.putExtra("callfor","admin");
                startActivity(i);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i=new Intent(getApplicationContext(),profile.class);
                   i.putExtra("username",username);
                   startActivity(i);
            }
        });
        teacherreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(getApplicationContext(),DIVERTER.class);
            i.putExtra("admin_username",username);
            startActivity(i);
            }
        });
        guardreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Main4Activity.class);
                i.putExtra("admin_username",username);
                startActivity(i);

            }
        });
        teacherdet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rv=new Intent(getApplicationContext(),Recyclerview_activity.class);
                rv.putExtra("callfor","teacher");
                rv.putExtra("admin_username",username);
                startActivity(rv);
            }
        });
        guarddet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rv=new Intent(getApplicationContext(),Recyclerview_activity.class);
                rv.putExtra("callfor","guard");
                rv.putExtra("admin_username",username);
                startActivity(rv);
            }
        });
        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),history.class);
                i.putExtra("username",username);
                startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database d=new database(Main5Activity.this);
                d.Delete(username,"admin");
                    stopService(new Intent(getApplicationContext(),background_service.class));
                    startService(new Intent(getApplicationContext(),background_service.class));
                                 finish();
            }
        });






    }
}
