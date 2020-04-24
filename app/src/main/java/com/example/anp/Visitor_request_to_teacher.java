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

public class Visitor_request_to_teacher extends AppCompatActivity {

    int length;
    int teacher_id;
    int admin_id;
    connection c;
    Statement statement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_request_to_teacher);
        RecyclerView view=findViewById(R.id.visitor_to_teacher_recycler);
        view.setHasFixedSize(true);
        view.setLayoutManager(new LinearLayoutManager(this));
        Intent i=getIntent();
        String username=i.getStringExtra("username");
        String callfor=i.getStringExtra("callfor");
        Date date=new Date();
        Timestamp today=new Timestamp(date.getTime());
        String timestamp=today.toString();
        String array[]=timestamp.split(" ");
        timestamp=array[0]+" 00:00:01";
        Timestamp timestamp1=new Timestamp(date.getTime());
        c=new connection();
         statement=c.get();
        if(statement==null) {
            c.establish();
            statement = c.get();
        }
        if(callfor.equals("teacher")) {
            try {
                ResultSet set = statement.executeQuery("select ID  from TEACHER  where USERNAME='" + username + "'");
                set.next();
                teacher_id = set.getInt(1);
                set=null;
                 set = statement.executeQuery("select count(*) from VISITOR WHERE TID=" + teacher_id + " and HASACCEPTED is NULL and hasmeet is null and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp1 + "'");
                set.next();
                length = set.getInt(1);
                set=null;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Retry ,Facing Some Problem ", Toast.LENGTH_LONG).show();
                finish();
            }
            if (length != 0) {
                String name[] = new String[length];
                String address[] = new String[length];
                String type[] = new String[length];
                String type_number[] = new String[length];
                String pn[] = new String[length];
                String entry[] = new String[length];
                String purpose[] = new String[length];
                String email[] = new String[length];
                int id[] = new int[length];

                int i1 = 0;
                boolean flag = true;
                try {
                    ResultSet set = statement.executeQuery("select NAME,ADDRESS,TYPE,TYPE_NUMBER,MOBILE,ENTRY,PURPOSE,EMAIL,ID,ADMIN_ID FROM VISITOR WHERE TID=" + teacher_id + " and HASACCEPTED is NULL and hasmeet is null and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp1 + "'");
                    while (set.next()) {
                        name[i1] = set.getString(1);
                        address[i1] = set.getString(2);
                        type[i1] = set.getString(3);
                        type_number[i1] = set.getString(4);
                        pn[i1] = set.getString(5);
                        entry[i1] = set.getString(6);
                        purpose[i1] = set.getString(7);
                        email[i1] = set.getString(8);
                        id[i1] = set.getInt(9);
                        if (flag) {
                            admin_id = set.getInt(10);
                            flag = false;
                        }
                        i1++;
                    }
                    set=null;
                    visitor_to_teacher_adapter adapter = new visitor_to_teacher_adapter(name, address, purpose, entry, pn, type, type_number, email, id, admin_id, this, length);
                    view.setAdapter(adapter);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Retry,Facing Some Problem", Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice");
                builder.setMessage("No Visitor Request Found");
                builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
            }
        }
        else
        {

            try {
                ResultSet set = statement.executeQuery("select ID  from ADMIN  where USERNAME='" + username + "'");
                set.next();
                admin_id= set.getInt(1);
                set=null;
                 set = statement.executeQuery("select count(*) from VISITOR WHERE ADMIN_ID=" + admin_id + " and HASACCEPTED is NULL and hasmeet is null  and timestampdiff(MINUTE,ENTRY,'"+timestamp1+"') and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp1 + "'");
                set.next();
                length = set.getInt(1);
                set=null;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Retry ,Facing Some Problem ", Toast.LENGTH_LONG).show();
                finish();
            }
            if (length != 0) {
                String name[] = new String[length];
                String address[] = new String[length];
                String type[] = new String[length];
                String type_number[] = new String[length];
                String pn[] = new String[length];
                String entry[] = new String[length];
                String purpose[] = new String[length];
                String email[] = new String[length];
                int id[] = new int[length];

                int i1 = 0;
                try {
                    ResultSet set = statement.executeQuery("select NAME,ADDRESS,TYPE,TYPE_NUMBER,MOBILE,ENTRY,PURPOSE,EMAIL,ID,ADMIN_ID FROM VISITOR WHERE TID=" + teacher_id + " and HASACCEPTED is NULL and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp1 + "'");
                    while (set.next()) {
                        name[i1] = set.getString(1);
                        address[i1] = set.getString(2);
                        type[i1] = set.getString(3);
                        type_number[i1] = set.getString(4);
                        pn[i1] = set.getString(5);
                        entry[i1] = set.getString(6);
                        purpose[i1] = set.getString(7);
                        email[i1] = set.getString(8);
                        id[i1] = set.getInt(9);
                        i1++;
                    }
                    set=null;
                    visitor_to_teacher_adapter adapter = new visitor_to_teacher_adapter(name, address, purpose, entry, pn, type, type_number, email, id, admin_id, this, length);
                    view.setAdapter(adapter);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Retry,Facing Some Problem", Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notice");
                builder.setMessage("No Visitor Request Found");
                builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
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
