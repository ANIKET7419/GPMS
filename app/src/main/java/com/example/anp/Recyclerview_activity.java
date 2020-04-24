package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class Recyclerview_activity extends AppCompatActivity {

int admin_id;
connection c;
Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_activity);
        Intent i=getIntent();
         c=new connection();
         statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        RecyclerView view=findViewById(R.id.recyclerview);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        String username=i.getStringExtra("admin_username");
        String callfor=i.getStringExtra("callfor");
        try {
            ResultSet set=  statement.executeQuery(" select ID from ADMIN WHERE USERNAME='" + username + "'");
            set.next();
            admin_id=set.getInt(1);
            set=null;
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        if(callfor.equals("teacher"))
        {
            try {

                ResultSet set2=statement.executeQuery("select count(*) from TEACHER where ADMIN_ID="+admin_id);
                set2.next();
                int length= set2.getInt(1);
                   set2=null;
                   if(length==0)
                   {
                       AlertDialog.Builder builder=new AlertDialog.Builder(this);
                       builder.setTitle("Notice");
                       builder.setMessage(" No Data ");
                       builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                          finish();
                           }
                       });
                       builder.show();
                   }
                   else
                   {

                       ResultSet set = statement.executeQuery("select USERNAME,NAME FROM TEACHER where ADMIN_ID="+admin_id);
                       String teacher [][]=new String[length][2];
                       int i1=0;
                       while(set.next())
                       {
                           teacher[i1][0]=set.getString(1);
                           teacher[i1][1]=set.getString(2);
                           i1++;
                       }
                       set=null;
                       teacher_adapter adapter=new teacher_adapter(getApplicationContext(),"teacher",teacher,length);
                       view.setAdapter(adapter);
                   }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
                }

        }
        if(callfor.equals("guard"))
        {
            try {
                ResultSet set2=statement.executeQuery("select count(*) from GUARD where ADMIN_ID="+admin_id);
                set2.next();
                int length= set2.getInt(1);
                set2=null;
                if(length==0)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("Notice");
                    builder.setMessage(" No Data ");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.show();
                }
                else
                {
                    ResultSet set = statement.executeQuery("select USERNAME,NAME FROM GUARD where ADMIN_ID="+admin_id);
                    String guard [][]=new String[length][2];
                    int i1=0;
                    while(set.next())
                    {
                        guard[i1][0]=set.getString(1);
                        guard[i1][1]=set.getString(2);
                        i1++;
                    }
                    set=null;
                    teacher_adapter adapter=new teacher_adapter(getApplicationContext(),"guard" ,guard,length);
                    view.setAdapter(adapter);
                }
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
