package com.example.anp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class Information extends AppCompatActivity {

    TextView name,address,dob,pn,email,dept,typenumber;
    String username;
    String callfor;
    connection c;
    Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        name=findViewById(R.id.info_name);
        address=findViewById(R.id.info_address);
        dob=findViewById(R.id.info_dob);
        pn=findViewById(R.id.info_phone);
        email=findViewById(R.id.info_email);
        dept=findViewById(R.id.info_deptt);
        typenumber=findViewById(R.id.info_typenumber);
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        Intent i=getIntent();
        username=i.getStringExtra("username");
        callfor=i.getStringExtra("callfor");
        if(callfor.equals("guard"))
        {
            try{
                ResultSet set=statement.executeQuery("select NAME,ADDRESS,MOBILE,DOB,EMAIL,TYPE,TYPE_NUMBER from GUARD WHERE USERNAME='"+username+"'");
                set.next();
                name.setText("  NAME        "+set.getString(1));
                address.setText("  ADDRESS    "+set.getString(2));
                dob.setText("  DOB        "+set.getString(4));
                email.setText("  EMAIL      "+set.getString(5));
                pn.setText("  MOBILE     "+set.getString(3));
                typenumber.setText("  "+set.getString(6)+"  :  "+set.getString(7));

                set=null;
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Some Network Problem,Retry",Toast.LENGTH_LONG).show();
                finish();
            }

        }
        else
        {
            try {

                ResultSet set = statement.executeQuery("select NAME,ADDRESS,MOBILE,DOB,EMAIL,TYPE,TYPE_NUMBER,DID from TEACHER WHERE USERNAME='" + username + "'");
                set.next();
                int did=set.getInt(8);
                name.setText("  NAME        " + set.getString(1));
                address.setText("  ADDRESS    " + set.getString(2));
                dob.setText("  DOB        " + set.getString(4));
                email.setText("  EMAIL      " + set.getString(5));
                pn.setText("  MOBILE     " + set.getString(3));
                typenumber.setText("  " + set.getString(6) + "  :  " + set.getString(7));
                set=null;
                 set=statement.executeQuery("select NAME FROM DEPARTMENT WHERE ID="+did);
                set.next();
                dept.setVisibility(TextView.VISIBLE);
                dept.setText("  DEPARTMENT   "+set.getString(1));

                set=null;
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Some Network Problem,Retry",Toast.LENGTH_LONG).show();
                finish();
            }
        }

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
