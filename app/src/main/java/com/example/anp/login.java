package com.example.anp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.sql.ResultSet;
import java.sql.Statement;

public class login extends AppCompatActivity {
EditText username,password;
database data;
connection c;
Statement statement;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        data=new database(this);
        password=findViewById(R.id.password);
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        LinearLayout t=findViewById(R.id.login);
       final Intent i=getIntent();
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i.getStringExtra("id").equals("admin")) {

                    try {

                      ResultSet set=statement.executeQuery("select count(*) from ADMIN where username='"+username.getText().toString()+"' and password='"+password.getText().toString()+"'");
                      set.next();
                      if(set.getInt(1)==1)
                      {
                          data.insert(username.getText().toString(),password.getText().toString(),"admin");
                          Intent i=new Intent(getApplicationContext(),Main5Activity.class);
                          i.putExtra("username",username.getText().toString());
                          stopService(new Intent(getApplicationContext(),background_service.class));
                          startService(new Intent(getApplicationContext(),background_service.class));
                          startActivity(i);
                      }
                      else
                      {
                          AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                          C.setTitle("Error");
                          C.setMessage("User Does Not Exist");
                          C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                  username.setText("");
                                  password.setText("");
                              }
                          });
                          C.show();

                      }

set=null;
                    }
                    catch (Exception e)
                    {
                        AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                        C.setTitle("Error");
                        C.setMessage("Retry,Facing Some Problem");
                        C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        C.show();

                    }
                }
                if(i.getStringExtra("id").equals("teacher")) {
                    try {

                        ResultSet set=statement.executeQuery("select count(*) from TEACHER where username='"+username.getText().toString()+"' and password='"+password.getText().toString()+"'");
                        set.next();
                        if(set.getInt(1)==1)
                        {
                            data.insert(username.getText().toString(),password.getText().toString(),"teacher");
                            Intent i=new Intent(getApplicationContext(),Main6Activity.class);
                            i.putExtra("username",username.getText().toString());
                            stopService(new Intent(getApplicationContext(),background_service.class));
                            startService(new Intent(getApplicationContext(),background_service.class));
                            startActivity(i);
                        }
                        else
                        {
                            AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                            C.setTitle("Error");
                            C.setMessage("User Does Not Exist");
                            C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    username.setText("");
                                    password.setText("");
                                }
                            });
                            C.show();

                        }
                        set=null;


                    }
                    catch (Exception e)
                    {
                        AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                        C.setTitle("Error");
                        C.setMessage("Retry,Facing Some Problem");
                        C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        C.show();

                    }

                }
                if(i.getStringExtra("id").equals("guard")) {

                    try {

                        ResultSet set=statement.executeQuery("select count(*) from GUARD where username='"+username.getText().toString()+"' and password='"+password.getText().toString()+"'");
                        set.next();
                        if(set.getInt(1)==1)
                        {



                            data.insert(username.getText().toString(),password.getText().toString(),"guard");
                            Intent i=new Intent(getApplicationContext(),Main7Activity.class);
                            i.putExtra("username",username.getText().toString());
                            stopService(new Intent(getApplicationContext(),background_service.class));
                            startService(new Intent(getApplicationContext(),background_service.class));                         startActivity(i);
                        }
                        else
                        {
                            AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                            C.setTitle("Error");
                            C.setMessage("User Does Not Exist");
                            C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    username.setText("");
                                    password.setText("");
                                }
                            });
                            C.show();

                        }
                        set=null;


                    }
                    catch (Exception e)
                    {
                        AlertDialog.Builder C=new AlertDialog.Builder(login.this);
                        C.setTitle("Error");
                        C.setMessage("Retry,Facing Some Problem");
                        C.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        C.show();

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
