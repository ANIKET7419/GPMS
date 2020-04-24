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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Visitor_registartion extends AppCompatActivity {

    EditText name;
    EditText uniqueid,address,mobile,email,dob,purpose;
    Spinner teacher_name,id_type;
    LinearLayout signup;
    String type="";
    String teacher_name_;
    connection c;
    Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_registartion);
        name=findViewById(R.id.visitor_name);
        uniqueid=findViewById(R.id.visitor_acn);
        address=findViewById(R.id.visitor_address);
        mobile=findViewById(R.id.visitor_mobile);
        email=findViewById(R.id.visitor_email);
        dob=findViewById(R.id.visitor_dob);
        purpose=findViewById(R.id.purpose);
        final ProgressBar bar=findViewById(R.id.progressBar_cyclicV);
        bar.setVisibility(View.INVISIBLE);
        teacher_name=findViewById(R.id.visitor_teacher);
        id_type=findViewById(R.id.visitor_id_type);
        signup=findViewById(R.id.visitor_signuplayout);
        ArrayList list=new ArrayList();
        list.add("Aadhaar C");
        list.add("PAN C");
        list.add("Driving L");
        list.add("Voter Id");
        list.add("Others");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);;
        id_type.setAdapter(adapter);
        id_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                int pos = id_type.getSelectedItemPosition();
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
        ArrayList teacher_list=new ArrayList();
        Intent i=getIntent();
       final String username=i.getStringExtra("username");
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
       final  int admin_id;
        try {

            ResultSet set=statement.executeQuery(" select ADMIN_ID FROM GUARD WHERE USERNAME='"+username+"'");
            set.next();
            admin_id=set.getInt(1);
            set=null;
            set=statement.executeQuery("select TEACHER.NAME,DEPARTMENT.NAME FROM TEACHER,DEPARTMENT WHERE DID=DEPARTMENT.ID AND TEACHER.ADMIN_ID="+admin_id);
            while(set.next())
            {
                teacher_list.add(set.getString(1)+","+set.getString(2).substring(0,4)+".");
            }
            set.close();
            ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, teacher_list);
            teacher_name.setAdapter(adapter2);
            teacher_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView parent, View view, int position, long id) {
                    teacher_name_= (String)teacher_name.getSelectedItem();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Retry, Facing Some Problem ",Toast.LENGTH_LONG).show();
            finish();
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bar.setVisibility(View.VISIBLE);

                boolean flag=false;

                String name1="";
                name1=name.getText().toString();
                String address1="";
                address1=address.getText().toString();
                String id=uniqueid.getText().toString();
                String pn1="";
                pn1=mobile.getText().toString();
                String email1="";
                email1=email.getText().toString();
                String dob1="";
                dob1=dob.getText().toString();
                String purpose1=purpose.getText().toString();
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
                if(name1.equals("")||address1.equals("")||pn1.equals("")||id.equals("")||dob1.equals("")||purpose1.equals(""))
                {

                    bar.setVisibility(View.INVISIBLE);
                    flag=true;
                    if(name1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Error");
                        builder.setMessage("Name is Required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(address1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Error");
                        builder.setMessage("Id is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(purpose1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Error");
                        builder.setMessage("Purpose is required !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                    }
                    if(pn1.equals(""))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
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
                    if(email_count!=2&&!email1.equals(""))
                    {
                        bar.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Error");
                        builder.setMessage("Email Address is Wrong !!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        flag=true;
                        builder.show();
                    }

                      if(dob1.charAt(4)!='-'&&(dob1.charAt(7)!='-'||dob1.charAt(6)!='-'))
                    {
                        bar.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
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
                        AlertDialog.Builder builder=new AlertDialog.Builder(Visitor_registartion.this);
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
                }
                if(flag==false)
                {
                    try {
                        ResultSet set2=statement.executeQuery(" select ADMIN_ID FROM GUARD WHERE USERNAME='"+username+"'");
                        set2.next();
                        int admin_id=set2.getInt(1);
                        String t_d[]=teacher_name_.split(",");
                        t_d[1]=t_d[1].substring(0,t_d[1].length()-1);
                       ResultSet set= statement.executeQuery("SELECT TEACHER.ID FROM TEACHER,DEPARTMENT WHERE TEACHER.DID=DEPARTMENT.ID AND TEACHER.NAME='"+t_d[0]+"' and DEPARTMENT.NAME LIKE '%"+t_d[1]+"%'");
                       set.next();
                       int tid=set.getInt(1);
                       java.util.Date date=new java.util.Date();
                        java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
                        if(email1.equals(""))
                            statement.execute("insert into VISITOR(NAME,ADDRESS,MOBILE,TYPE,TYPE_NUMBER,DOB,TID,PURPOSE,ENTRY,ADMIN_ID) values ('"+name1+"','"+address1+"','"+pn1+"','"+type+"','"+id+"','"+dob1+"',"+tid+",'"+purpose1+"','"+sqlTime+"',"+admin_id+")");
                        else
                            statement.execute("insert into VISITOR(NAME,ADDRESS,MOBILE,TYPE,TYPE_NUMBER,DOB,TID,PURPOSE,ENTRY,ADMIN_ID,EMAIL) values ('"+name1+"','"+address1+"','"+pn1+"','"+type+"','"+id+"','"+dob1+"',"+tid+",'"+purpose1+"','"+sqlTime+"',"+admin_id+",'"+email1+"')");
                        AlertDialog.Builder builder =new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Congratulation !!!! ");
                        builder.setMessage("Entry Has Been Made.");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mobile.setText("");
                                dob.setText("");
                                name.setText("");
                                email.setText("");
                                purpose.setText("");
                                address.setText("");
                                uniqueid.setText("");
                                finish();
                            }
                        });
                        bar.setVisibility(View.INVISIBLE);
                        builder.show();
                    }
                    catch (Exception e)
                    {
                        bar.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder =new AlertDialog.Builder(Visitor_registartion.this);
                        builder.setTitle("Exception");
                        builder.setMessage(e.getMessage());
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                        if(e.getMessage().equals("Data truncation:Data too long for column 'MOBILE' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Visitor_registartion.this);
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
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Visitor_registartion.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Email Address  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'PASSWORD' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Visitor_registartion.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Password  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'PURPOSE' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Visitor_registartion.this);
                            builder1.setTitle("Error");
                            builder1.setMessage("Purpose Field  Is Too Long");
                            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder1.show();
                        }
                        if(e.getMessage().equals("Data truncation:Data too long for column 'ADDRESS' at row 1")) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Visitor_registartion.this);
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
