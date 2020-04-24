package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class DEPARTMENT extends AppCompatActivity {

    EditText dp1,dp2,dp3,dp4,dp5;
    LinearLayout submit;
    int admin_id;
    connection c;
    Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        Intent i=getIntent();
        String username=i.getStringExtra("admin_username");
         c=new connection();
         statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        try {
            ResultSet set=statement.executeQuery("select ID FROM ADMIN WHERE USERNAME='"+username+"'");
            set.next();
            admin_id=set.getInt(1);
            set=null;
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dp1=findViewById(R.id.dp1);
        dp2=findViewById(R.id.dp2);
        dp3=findViewById(R.id.dp3);
        dp4=findViewById(R.id.dp4);
        dp5=findViewById(R.id.dp5);
        submit=findViewById(R.id.submit_layout);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (!dp1.getText().toString().equals("")) {
                        statement.execute("insert into DEPARTMENT (NAME,ADMIN_ID) VALUES ('"+dp1.getText().toString()+"',"+admin_id+")");
                    }
                    if (!dp2.getText().toString().equals("")) {
                        statement.execute("insert into DEPARTMENT (NAME,ADMIN_ID) VALUES ('"+dp2.getText().toString()+"',"+admin_id+")");
                    }
                    if (!dp3.getText().toString().equals("")) {
                        statement.execute("insert into DEPARTMENT (NAME,ADMIN_ID) VALUES ('"+dp3.getText().toString()+"',"+admin_id+")");
                    }
                    if (!dp4.getText().toString().equals("")) {
                        statement.execute("insert into DEPARTMENT (NAME,ADMIN_ID) VALUES ('"+dp4.getText().toString()+"',"+admin_id+")");
                    }
                    if (!dp5.getText().toString().equals("")) {
                        statement.execute("insert into DEPARTMENT (NAME,ADMIN_ID) VALUES ('"+dp5.getText().toString()+"',"+admin_id+")");
                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(DEPARTMENT.this);
                    builder.setTitle("Congratulation ..");
                    builder.setMessage("Data Has Been Inserted");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                       dp1.setText("");
                       dp2.setText("");
                       dp3.setText("");
                       dp4.setText("");
                       dp5.setText("");
                        }
                    });
                    builder.show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext()," Facing Some Problem , Retry",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    @Override
    public  void onResume()
    {
        super.onResume();
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }

    }
}
