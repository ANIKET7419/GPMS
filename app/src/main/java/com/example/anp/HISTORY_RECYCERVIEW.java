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

public class HISTORY_RECYCERVIEW extends AppCompatActivity {

    RecyclerView view;
    String name[];
    String address[];
    String email[];
    String mobile[];
    String hasaccepted[];
    String hasmeet[];
    String entry[];
    String exit[];
    String teacher[];
    String department[];
    String purpose[];
    String type[];
    String type_number[];
    int admin_id;
    String starting;
    String ending;
    String admin_username;
    int length=0;
    Statement statement;
    connection c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__recycerview);
        view=findViewById(R.id.recyclerview_history);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        Intent i=getIntent();
        admin_username=i.getStringExtra("username");
        starting=i.getStringExtra("starting");
        ending=i.getStringExtra("ending");
        starting+=" 00:00:01";
        ending+=" 23:59:59";
        c=new connection();
        statement=c.get();
        if(statement==null)
        {
            c.establish();
            statement=c.get();
        }
        try {
            ResultSet set=null;
            set=statement.executeQuery("select ID FROM ADMIN WHERE USERNAME='"+admin_username+"'");
            set.next();
            admin_id=set.getInt(1);
            set=null;

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Retry,Facing Some Problem",Toast.LENGTH_LONG).show();
            finish();
        }

        try {

            ResultSet set=null;
            set=statement.executeQuery("Select count(*) from VISITOR where ADMIN_ID="+admin_id+" and ENTRY BETWEEN '"+starting+"' and '"+ending+"'");
            set.next();
            length=set.getInt(1);
            set=null;
            if(length==0)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Notice....");
                builder.setTitle("No Data Found");
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
                 name=new String[length];
                address=new String[length];
                email=new String[length];
                 mobile=new String[length];
                 hasaccepted=new String[length];
                 hasmeet=new String[length];
                entry=new String[length];
                 exit=new String[length];
                teacher=new String[length];
                department=new String[length];
                purpose=new String[length];
                type=new String[length];
                type_number=new String[length];
                ResultSet set2=null;
                set2=statement.executeQuery("SELECT VISITOR.NAME, VISITOR.ADDRESS,VISITOR.EMAIL,VISITOR.MOBILE,HASACCEPTED,hasmeet,ENTRY,EXIT_TIME,TEACHER.NAME,DEPARTMENT.NAME,PURPOSE,VISITOR.TYPE,VISITOR.TYPE_NUMBER FROM VISITOR,TEACHER,DEPARTMENT WHERE VISITOR.TID=TEACHER.ID AND TEACHER.DID=DEPARTMENT.ID and VISITOR.ADMIN_ID="+admin_id+" and ENTRY BETWEEN '"+starting+"' and '"+ending+"'");
                int i1=0;
                while (set2.next())
                {
                    name[i1]=set2.getString(1);
                    address[i1]=set2.getString(2);
                    email[i1]=set2.getString(3);
                    mobile[i1]=set2.getString(4);
                    hasaccepted[i1]=set2.getString(5);
                    hasmeet[i1]=set2.getString(6);
                    entry[i1]=set2.getString(7);
                    exit[i1]=set2.getString(8);
                    teacher[i1]=set2.getString(9);
                    department[i1]=set2.getString(10);
                    purpose[i1]=set2.getString(11);
                    type[i1]=set2.getString(12);
                    type_number[i1]=set2.getString(13);
                    i1++;
                    if(i1==length)
                        break;
                }
                set2=null;
                HISTORY_ADAPTER adapter=new HISTORY_ADAPTER(name,address,email,mobile,hasaccepted,hasmeet,entry,exit,teacher,department,purpose,type,type_number,length,this);
                view.setAdapter(adapter);




            }

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            finish();
        }

    }
    @Override
    public  void onResume()
    {
        super.onResume();
        c=new connection();
        statement=c.get();
        if(statement==null)
        {
            c.establish();
            statement=c.get();
        }
    }
}
