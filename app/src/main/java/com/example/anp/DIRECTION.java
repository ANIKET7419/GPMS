package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class DIRECTION extends AppCompatActivity {

    database d;
    LinearLayout registration;
    LinearLayout login;
    connection c;
    Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        d=new database(this);
        registration=findViewById(R.id.admin_registrationb);
        login=findViewById(R.id.admin_loginb);
         c=new connection();
       statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(I);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=new database(DIRECTION.this);
               final Cursor c=d.fetch("admin");
                c.moveToFirst();
                if(c.getCount()==0) {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    i.putExtra("id", "admin");
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(getApplicationContext(),Main5Activity.class);
                    try
                    {
                        ResultSet set=statement.executeQuery("select count(*) from ADMIN where USERNAME='"+c.getString(0)+"'");
                        set.next();
                        if(set.getInt(1)==0)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(DIRECTION.this);
                            builder.setTitle("Error");
                            builder.setMessage("Your Account is no more");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    d.Delete(c.getString(0),"admin");

                                }
                            });
                            builder.show();
                        }
                        else
                        {
                            i.putExtra("username",c.getString(0));
                            startActivity(i);
                        }
                        set=null;
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Retry, Facing Some Problem",Toast.LENGTH_LONG).show();
                        recreate();
                    }

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
