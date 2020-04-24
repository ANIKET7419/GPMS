package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class profile extends AppCompatActivity {

    EditText oldpassword;
    EditText newpassword;
    EditText acn;
    EditText address;
    EditText pn;
    EditText name;
    TextView profile;
    EditText email;
    Spinner spinner;
    String type="";
    connection c;
    Statement statement;

    LinearLayout update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i=getIntent();
        final String username=i.getStringExtra("username");
        profile=findViewById(R.id.profile);
        profile.setText("Profile : "+username);
        oldpassword=findViewById(R.id.admin_old_password_p);
        acn=findViewById(R.id.admin_acn_p);
        address=findViewById(R.id.admin_address_p);
        pn=findViewById(R.id.admin_mobilenumber_p);
        name=findViewById(R.id.admin_name_p);
        email=findViewById(R.id.admin_mail_p);
        update=findViewById(R.id.admin_update_p);
        newpassword=findViewById(R.id.admin_new_password_p);
        spinner=findViewById(R.id.admin_id_type_p);
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        ArrayList list=new ArrayList();
        list.add("Aadhaar C");
        list.add("PAN C");
        list.add("Driving L");
        list.add("Voter Id");
        list.add("Others");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                int pos = spinner.getSelectedItemPosition();
                switch (pos) {
                    case 0:
                        type = "Aadhaar Card";
                        break;
                    case 1:
                        type = "Pan Card";
                        break;
                    case 2:
                        type="Driving Licence";
                        break;
                    case 3:
                        type="Voter Id";
                        break;
                    case 4:
                        type="Others";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name1="";
                name1=name.getText().toString();
                String oldpassword1="";
                oldpassword1=oldpassword.getText().toString();
                String newpassword1="";
                newpassword1=newpassword.getText().toString();
                String address1="";
                address1=address.getText().toString();
                String id=acn.getText().toString();
                String pn1="";
                pn1=pn.getText().toString();
                String email1="";
                email1=email.getText().toString();
                int email_count=0;
                for(int i=0;i<email1.length();i++)
                {
                    if(email1.charAt(i)=='@')
                    {
                        int k=i;
                        i=email1.length();
                        email_count++;
                        for(int j=k+1;j<email1.length();j++)
                        {
                            if(email1.charAt(j)=='.')
                            {
                                if(email1.substring(j+1).equals("com"))
                                {
                                    email_count++;
                                    break;
                                }
                            }
                        }

                    }
                }
               boolean flag=false;
                if(name1.equals("")||address1.equals("")||newpassword1.equals("")||oldpassword1.equals("")||pn1.equals("")||email_count!=2||id.equals(""))
                {

                    flag=true;
                    if(email_count!=2)
                    {
                        email.setText("");
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Email Address is wrong !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(name1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Name is Required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(oldpassword1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage(" Old Password is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(address1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Address is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(id.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Id is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(newpassword1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("New Password is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(pn1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Phone Number is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }

                }
                else
                {
                    pn1=pn1.trim();
                    if(pn1.length()<10)
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                        builder.setTitle("Error");
                        builder.setMessage("Phone Number is not valid !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        flag=true;
                        builder.show();

                    }
                    try {
                        ResultSet set=statement.executeQuery("select count(*) from ADMIN WHERE USERNAME='" + username+"' and PASSWORD='"+oldpassword1+"'");
                        set.next();
                        if(set.getInt(1)==0)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(profile.this);
                            builder.setTitle("Error");
                            builder.setMessage("Old Password is not matched !!!");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            flag=true;
                            builder.show();
                        }
                        set=null;
                    }
                    catch (Exception e)
                    {

                        flag=true;
                        Toast.makeText(profile.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                if(flag==false) {
                    try {

                        statement.execute("update ADMIN set NAME='" + name1 + "', PASSWORD='" + newpassword1 + "',ADDRESS='" + address1 + "',TYPE='" + type + "',TYPE_NUMBER='" + id + "',EMAIL='" + email1 + "', MOBILE='" + pn1 + "' where USERNAME='" + username + "'");
                        AlertDialog.Builder builder = new AlertDialog.Builder(profile.this);
                        builder.setTitle("Congratulation !!!");
                        builder.setMessage("Account Has Been Modified");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                name.setText("");
                                oldpassword.setText("");
                                address.setText("");
                                pn.setText("");
                                acn.setText("");
                                newpassword.setText("");
                                email.setText("");
                                finish();
                            }
                        });
                        builder.show();
                    } catch (Exception e) {
                        Toast.makeText(profile.this, e.toString(), Toast.LENGTH_LONG).show();
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
