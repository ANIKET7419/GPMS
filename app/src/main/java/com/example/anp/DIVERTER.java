package com.example.anp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DIVERTER extends AppCompatActivity {


    LinearLayout d;
    LinearLayout t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diverter);
        Intent i=getIntent();
        final String username=i.getStringExtra("admin_username");
        d=findViewById(R.id.d_layout);
        t=findViewById(R.id.t_layout);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),DEPARTMENT.class);
                i.putExtra("admin_username",username);
                startActivity(i);

            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Main3Activity.class);
                i.putExtra("admin_username",username);
                startActivity(i);

            }
        });
    }
}
