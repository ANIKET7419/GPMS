package com.example.anp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class HAS_MEET extends AppCompatActivity {

    EditText id;
    Button find;
    Spinner type;
    String type1;
    connection c;
    Statement statement;
    String username;
    String callfor;
    int tid;
    int admin_id;
    String timestamp;
    Timestamp timestamp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_has__meet);
        id=findViewById(R.id.hasmeet_unique_id);
        find=findViewById(R.id.Find);
        type=findViewById(R.id.hasmeet_spinner);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        callfor=i.getStringExtra("callfor");
        c=new connection();
       final Date today=new Date();
        Timestamp t=new Timestamp(today.getTime());
        timestamp=t.toString();
        String array[]=timestamp.split(" ");
        timestamp=array[0]+" 00:00:01 ";
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
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                int pos = type.getSelectedItemPosition();
                switch (pos) {
                    case 0:
                        type1 = "Aadhaar Card";
                        break;
                    case 1:
                        type1 = "Pan Card";
                        break;
                    case 2:
                        type1="Driving Licence";
                        break;
                    case 3:
                        type1="Voter Id";
                        break;
                    case 4:
                        type1="Others";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timestamp1=new Timestamp(today.getTime());
                if (callfor.equals("teacher")) {

                    try {

                        ResultSet set1 = statement.executeQuery("select ID FROM TEACHER WHERE USERNAME='" + username + "'");
                        set1.next();
                        tid = set1.getInt(1);
                        set1=null;
                        set1 = statement.executeQuery("select count(*) from VISITOR where TID=" + tid + " and hasmeet IS NULL AND HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString() + "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                        set1.next();
                        int count = set1.getInt(1);
                        if (count == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(HAS_MEET.this);
                            builder.setTitle("Hello "+username);
                            builder.setMessage(" No Entry Found ");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        } else {
                            ResultSet set3 = statement.executeQuery("Select NAME,ADDRESS,TYPE,TYPE_NUMBER,ENTRY,MOBILE,EMAIL FROM VISITOR WHERE TID=" + tid + " and hasmeet is NULL and HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString()+ "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                            set3.next();
                            String message = set3.getString(1) + "\n" + set3.getString(2) + "\n" + set3.getString(3) + " " + set3.getString(4) + "\n" + set3.getString(5) + "\n" + set3.getString(6) + "\n" + set3.getString(7);
                            AlertDialog.Builder builder = new AlertDialog.Builder(HAS_MEET.this);
                            builder.setTitle("Hello "+username);
                            builder.setMessage(message);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {


                                        statement.execute("update VISITOR SET hasmeet='True' where TID=" + tid + " and hasmeet is NULL and HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString() + "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                                    } catch (Exception e) {
                                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(HAS_MEET.this);
                                        dialog1.setTitle("Error");
                                        dialog1.setMessage("Facing some Problem");
                                        dialog1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog1.show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }

                    } catch (Exception e) {
                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(HAS_MEET.this);
                        dialog1.setTitle("Error");
                        dialog1.setMessage("Facing some Problem");
                        dialog1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog1.show();

                    }
                }
                else
                {
                    try {
                        ResultSet set1 = statement.executeQuery("select ADMIN_ID FROM GUARD WHERE USERNAME='" + username + "'");
                        set1.next();
                        admin_id = set1.getInt(1);
                        set1=null;
                         set1 = statement.executeQuery("select count(*) from VISITOR where ADMIN_ID=" + admin_id + " and hasmeet IS NOT NULL AND HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString() + "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                        set1.next();
                        int count = set1.getInt(1);
                        if (count == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(HAS_MEET.this);
                            builder.setTitle("Hello "+username);
                            builder.setMessage(" No Meeting Found ");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        } else {
                            ResultSet set3 = statement.executeQuery("Select NAME,ADDRESS,TYPE,TYPE_NUMBER,ENTRY,MOBILE,EMAIL FROM VISITOR WHERE ADMIN_ID=" + admin_id + " and hasmeet is NOT NULL and HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString() + "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                            set3.next();
                            String message = set3.getString(1) + "\n" + set3.getString(2) + "\n" + set3.getString(3) + " " + set3.getString(4) + "\n" + set3.getString(5) + "\n" + set3.getString(6) + "\n" + set3.getString(7);
                            AlertDialog.Builder builder = new AlertDialog.Builder(HAS_MEET.this);
                            builder.setTitle("Hello "+username);
                            builder.setMessage(message);
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {

                                        Date date=new Date();
                                        Timestamp timestamp=new Timestamp(date.getTime());
                                        statement.execute("update VISITOR SET EXIT_TIME='"+timestamp+"' where ADMIN_ID=" + admin_id + " and hasmeet is not NULL and HASACCEPTED IS NOT NULL AND TYPE='" + type1 + "' and TYPE_NUMBER='" + id.getText().toString() + "' and ENTRY BETWEEN '"+timestamp+"' and '"+timestamp1+"'");
                                    } catch (Exception e) {
                                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(HAS_MEET.this);
                                        dialog1.setTitle("Error");
                                        dialog1.setMessage("Facing some Problem");
                                        dialog1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        dialog1.show();
                                    }
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                        }

                    } catch (Exception e) {
                        AlertDialog.Builder dialog1 = new AlertDialog.Builder(HAS_MEET.this);
                        dialog1.setTitle("Error");
                        dialog1.setMessage("Facing some Problem");
                        dialog1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog1.show();

                    }
                }
            }
        });
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
