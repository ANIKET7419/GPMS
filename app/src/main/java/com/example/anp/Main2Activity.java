package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText acn;
    EditText address;
    EditText pn;
    EditText name;
    EditText email;
    EditText dob;
    LinearLayout signup;
    Spinner spinner;
    String type="";
    connection c;
    Statement statement;
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        username=findViewById(R.id.admin_username);
        password=findViewById(R.id.admin_password);
        acn=findViewById(R.id.admin_acn);
        address=findViewById(R.id.admin_address);
        pn=findViewById(R.id.admin_mobilenumber);
        name=findViewById(R.id.admin_name);
        email=findViewById(R.id.admin_mail);
        final ProgressBar bar=findViewById(R.id.progressBar_cyclic2);
        bar.setVisibility(View.INVISIBLE);
        dob=findViewById(R.id.admin_dob);
        dob.setHint("YYYY-MM-DD");
        signup=findViewById(R.id.admin_signupl);
        spinner=findViewById(R.id.type);
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
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);;
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
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                flag=false;
                String name1="";
                name1=name.getText().toString();
                String pass1="";
                pass1=password.getText().toString();
                String username1="";
                username1=username.getText().toString();
                String address1="";
                address1=address.getText().toString();
                String id=acn.getText().toString();
                String pn1="";
                pn1=pn.getText().toString();
                String email1="";
                email1=email.getText().toString();
                String dob1="";
                dob1=dob.getText().toString();
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

                if(name1.equals("")||address1.equals("")||pass1.equals("")||username1.equals("")||pn1.equals("")||email_count!=2||id.equals("")||dob1.equals(""))
                {
                    bar.setVisibility(View.INVISIBLE);

                    flag=true;
                    if(email_count!=2)
                    {
                        email.setText("");
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Name is Required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(pass1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Password is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(address1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Id is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(username1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Username is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(pn1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("Phone Number is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(dob1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("DOB is required !!!");
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

                    if(dob1.charAt(4)!='-'&&(dob1.charAt(7)!='-'||dob1.charAt(6)!='-'))
                    {
                        bar.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Error");
                        builder.setMessage("DOB should be in format (YYYY-MM-DD) !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        flag=true;
                        builder.show();
                    }
                   pn1= pn1.trim();
                    if(pn1.length()<10)
                    {
                        bar.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
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
                        bar.setVisibility(View.VISIBLE);
                        ResultSet set=statement.executeQuery("select count(*) from ADMIN WHERE USERNAME='"+username1+"'");
                        set.next();
                        if(set.getInt(1)==1)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Username is already exist !!!");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            flag=true;
                            bar.setVisibility(View.INVISIBLE);
                            builder.show();
                        }
                    }
                    catch (Exception e)
                    {

                        bar.setVisibility(View.INVISIBLE);
                        flag=true;
                        Toast.makeText(Main2Activity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                if(flag==false)
                {
                    try {

                        statement.execute("insert into ADMIN(NAME,USERNAME,PASSWORD,ADDRESS,MOBILE,TYPE,TYPE_NUMBER,DOB,EMAIL )  values ('"+name1+"','"+username1+"','"+pass1+"','"+address1+"','"+pn1+"','"+type+"','"+id+"','"+dob1+"','"+email1+"')");
                        AlertDialog.Builder builder =new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Congratulation !!!! ");
                        builder.setMessage("Account Has Been Created\nTo Login Just Press Back Button And Tap Login Option");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                pn.setText("");
                                dob.setText("");
                                name.setText("");
                                username.setText("");
                                email.setText("");
                                password.setText("");
                                address.setText("");
                                acn.setText("");
                                finish();
                            }
                        });
                        bar.setVisibility(View.INVISIBLE);
                        builder.show();
                    }
                    catch (Exception e)
                    {
                        bar.setVisibility(View.INVISIBLE);
                        if(e.getMessage().equals("Duplicate entry '"+email1+"' for key 'EMAIL'")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Email Address is already being used");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                        if(e.getMessage().equals("Duplicate entry '"+id+"' for key 'TYPE_NUMBER'")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Unique Id is already being used");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                        if(e.getMessage().equals("Duplicate entry '"+pn1+"' for key 'MOBILE'")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Mobile Number is already being used");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'MOBILE' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Mobile Number Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'EMAIL' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Email Address  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'USERNAME' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Username Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'PASSWORD' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Password  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'ADDRESS' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Main2Activity.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Address  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
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
