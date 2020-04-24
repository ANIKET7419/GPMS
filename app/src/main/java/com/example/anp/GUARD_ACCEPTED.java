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
import java.sql.Timestamp;
import java.util.Date;

public class GUARD_ACCEPTED extends AppCompatActivity {

    String guard_username;
    connection c;
    Statement statement;
    int admin_id;
    String callfor;
    String timestamp;
    Timestamp timestamp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard__accepted);
        Intent i=getIntent();
        guard_username=i.getStringExtra("username");
        callfor=i.getStringExtra("callfor");
        c=new connection();
        statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        Date today=new Date();
        Timestamp t=new Timestamp(today.getTime());
        timestamp=t.toString();
        String array[]=timestamp.split(" ");
        timestamp=array[0]+" 00:00:01 ";
        timestamp1=new Timestamp(today.getTime());
        RecyclerView view=findViewById(R.id.guard_accepted_recyclerview);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        try {
            ResultSet set=statement.executeQuery("select ADMIN_ID FROM GUARD WHERE USERNAME='"+guard_username+"'");
            set.next();
            admin_id=set.getInt(1);
            set=null;
            if(callfor.equals("accepted")) {
                set = statement.executeQuery("select count(*) FROM VISITOR WHERE HASACCEPTED IS NOT NULL AND hasmeet is NULL and ADMIN_ID=" + admin_id +" and ENTRY BETWEEN '" + timestamp + "' and  '" + timestamp1 + "'");
            }
            else
            {
                set = statement.executeQuery("select count(*) FROM VISITOR WHERE HASACCEPTED IS  NULL AND hasmeet is NULL and ADMIN_ID=" + admin_id + " and ENTRY BETWEEN '" + timestamp + "' and  '" + timestamp1 + "'");
            }
            set.next();
            int length=set.getInt(1);
            set.close();
            if(length==0)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Notice");
                builder.setMessage("No Data Found");
                builder.setPositiveButton("OKay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
            else {
                ResultSet set2=null;
                if(callfor.equals("accepted"))
                 set2 = statement.executeQuery("select NAME,ADDRESS,MOBILE,TYPE,TYPE_NUMBER,ENTRY FROM VISITOR WHERE HASACCEPTED IS NOT NULL AND hasmeet is NULL and ADMIN_ID=" + admin_id+" and ENTRY BETWEEN '"+timestamp+"' and  '"+timestamp1+"'");
                else
                    set2 = statement.executeQuery("select NAME,ADDRESS,MOBILE,TYPE,TYPE_NUMBER,ENTRY FROM VISITOR WHERE HASACCEPTED IS  NULL AND hasmeet is NULL and ADMIN_ID=" + admin_id+" and ENTRY BETWEEN '"+timestamp+"' and  '"+timestamp1+"'");
                String name[]=new String[length];
                String address[]=new String[length];
                String mobile[]=new String[length];
                String type[]=new String[length];
                String number[]=new String[length];
                String entry[]=new String[length];
                int i1=0;
                while (set2.next())
                {
                    name[i1]=set2.getString(1);
                    address[i1]=set2.getString(2);
                    mobile[i1]=set2.getString(3);
                    type[i1]=set2.getString(4);
                    number[i1]=set2.getString(5);
                    entry[i1]=set2.getString(6);
                    i1++;
                }
                set2=null;
                guard_acc_notacc_adapter adapter=new guard_acc_notacc_adapter(name,address,type,number,entry,mobile,length);
                view.setAdapter(adapter);


            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Some Network Problem,Retry",Toast.LENGTH_LONG).show();
            finish();
        }





    }
    @Override
    public void onResume()
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
