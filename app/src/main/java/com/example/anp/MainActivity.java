package com.example.anp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView w,us;
    LinearLayout admin,teacher,guard;
    TextView t,a,g;
    connection make;
    Statement statement;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
         bar=findViewById(R.id.progressBar_cyclic);
        bar.setVisibility(View.INVISIBLE);
        w=findViewById(R.id.welcome);
        us=findViewById(R.id.us);
        admin=findViewById(R.id.adminl);
        teacher=findViewById(R.id.teacherl);
        guard=findViewById(R.id.guardl);
        t=findViewById(R.id.teacher);
        g=findViewById(R.id.guard);
        a=findViewById(R.id.admin);
        make=new connection();
        bar.setVisibility(View.VISIBLE);
        make.establish();
        statement=make.get();
        bar.setVisibility(View.INVISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("GPMS_ID","GPMS",NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("It is GPMS Notification Channel");
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        final database d=new database(this);
        YoYo.with(Techniques.Bounce).repeat(YoYo.INFINITE).delay(0).playOn(w);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(t);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(a);
        YoYo.with(Techniques.Flash).repeat(YoYo.INFINITE).delay(0).playOn(g);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DIRECTION.class);
                startActivity(i);

            }});
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Cursor c=d.fetch("teacher");
                c.moveToFirst();
                if(c.getCount()==0) {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    i.putExtra("id", "teacher");
                    startActivity(i);
                }
                else
                {
                    Intent i=new Intent(getApplicationContext(),Main6Activity.class);
                    try
                    {
                        bar.setVisibility(View.VISIBLE);
                        ResultSet set=statement.executeQuery("select count(*) from TEACHER where USERNAME='"+c.getString(0)+"'");
                        set.next();
                        bar.setVisibility(View.INVISIBLE);
                        if(set.getInt(1)==0)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Your Account is no more");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    d.Delete(c.getString(0),"teacher");
                                    finish();
                                }
                            });
                            builder.show();
                        }
                        else
                        {
                            set=null;
                            i.putExtra("username",c.getString(0));
                            startActivity(i);
                        }
                        set=null;
                    }
                    catch (Exception e)
                    {

                        bar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Retry,Facing Some Problem ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        guard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Cursor c=d.fetch("guard");
                c.moveToFirst();
                if(c.getCount()==0) {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    i.putExtra("id", "guard");
                    startActivity(i);
                }
                else
                {

                    Intent i=new Intent(getApplicationContext(),Main7Activity.class);
                    try
                    {
                        bar.setVisibility(View.VISIBLE);
                        ResultSet set=statement.executeQuery("select count(*) from GUARD where USERNAME='"+c.getString(0)+"'");
                        set.next();
                        bar.setVisibility(View.INVISIBLE);
                        if(set.getInt(1)==0)
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Error");
                            builder.setMessage("Your Account is no more");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    d.Delete(c.getString(0),"guard");
                                }
                            });
                            builder.show();
                        }
                        else
                        {
                            set=null;
                            i.putExtra("username",c.getString(0));
                            startActivity(i);
                        }
                        set=null;

                    }
                    catch (Exception e)
                    {
                        bar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Retry,Facing Some Problem ",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),About_us.class);
                startActivity(i);
            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random i= new Random();
                        int k=i.nextInt()%10;

                        if(k<5) {
                            us.setTextColor(Color.GREEN);
                            w.setTextColor(Color.GREEN);
                        }
                        else {
                            us.setTextColor(Color.YELLOW);
                            w.setTextColor(Color.YELLOW);
                        }
                    }
                });

            }
        }, 0, 1000);

    }
    @Override
    public void onResume()
    {
        super.onResume();
        make=new connection();
        statement=make.get();
        if(statement==null) {
            bar.setVisibility(View.VISIBLE);
            make.establish();
            bar.setVisibility(View.INVISIBLE);
            statement = make.get();
        }



    }

}
