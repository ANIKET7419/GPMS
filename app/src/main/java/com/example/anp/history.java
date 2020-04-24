package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class history extends AppCompatActivity {

    EditText starting;
    EditText ending;
    LinearLayout go;
    String first;
    String second;
    boolean flag=true;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        starting=findViewById(R.id.startingdate);
        ending=findViewById(R.id.lastdate);
        go=findViewById(R.id.go);
        Intent i1=getIntent();
        username=i1.getStringExtra("username");
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                if(starting.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Starting Date",Toast.LENGTH_SHORT).show();
                    flag=false;
                }
                if(ending.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Last  Date",Toast.LENGTH_SHORT).show();
                    flag=false;

                }
                if(flag)
                {
                    first=starting.getText().toString();
                    second=ending.getText().toString();
                    try {


                        if (first.charAt(4) != '-' && (first.charAt(7) != '-' || first.charAt(6) != '-')) {
                            Toast.makeText(getApplicationContext(),"First Date Format is Wrong",Toast.LENGTH_LONG).show();
                            starting.setText("");
                           flag=false;
                        }
                        if (second.charAt(4) != '-' && (second.charAt(7) != '-' || second.charAt(6) != '-')) {
                            Toast.makeText(getApplicationContext()," Last Date Format is Wrong",Toast.LENGTH_LONG).show();
                            ending.setText("");
                            flag=false;
                        }
                    }
                    catch (Exception e)
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(),"Date Format is Wrong",Toast.LENGTH_LONG).show();
                        starting.setText("");
                        ending.setText("");
                    }
                }
                if(flag)
                {
                    Intent i=new Intent(getApplicationContext(),HISTORY_RECYCERVIEW.class);
                    i.putExtra("starting",first);
                    i.putExtra("ending",second);
                    i.putExtra("username",username);
                    startActivity(i);
                }
            }
        });


    }
}
