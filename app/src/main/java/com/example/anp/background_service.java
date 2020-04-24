package com.example.anp;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class background_service extends Service
{
    connection c;
    Statement statement;
    database d;
    String t_username;
    String a_username,g_username;
    int admin_id;
    int g_admin_id;
    int t_id;
    @Override
    public void onCreate() {


    }

    public void notification(String username,String callfor)
    {
        try {
            Intent i = new Intent(this, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"GPMS_ID");
            builder.setContentIntent(pi);
            builder.setSmallIcon(R.drawable.ic_textsms_black_24dp);
            builder.setContentText(callfor);
            builder.setSubText("Some Entries Have Been Updated");
            builder.setContentTitle(username);
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.setSound(RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION));
            NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);

            managerCompat.notify(0, builder.build());

        }
        catch ( Exception e)
        {
            Log.e("admin","Notification "+e.toString());
        }
    }
    @Override
    public int onStartCommand(Intent intent, int i,int startid)
    {
        super.onStartCommand(intent,i,startid);

        try {
            c = new connection();
            statement = c.get();
            if(statement==null)
            {
                c.establish();
                statement=c.get();
            }
            d = new database(this);
            Cursor s1 = d.fetch("admin");
        if(s1.getCount()!=0)
        {
            s1.moveToFirst();
            a_username=s1.getString(0);
         new Thread( new Runnable(){
                @Override
                public void run()
                {
                    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                    c=new connection();
                    statement=c.get();
                    try {
                        Thread.sleep(10000);
                    }
                    catch (Exception e){}
                    Date today=new Date();
                    Timestamp t=new Timestamp(today.getTime());
                    String  timestamp1=t.toString();
                    String array[]=timestamp1.split(" ");
                    timestamp1=array[0]+" 00:00:01 ";
                    boolean flag=true;
                    while (flag) {
                        try {

                            ResultSet set1 = statement.executeQuery("SELECT ID FROM ADMIN WHERE USERNAME='" + a_username + "'");
                            set1.next();
                            admin_id = set1.getInt(1);
                            set1.close();
                            flag=false;
                        } catch (Exception e) {
                            Log.e("admin", "Outer-Admin " + e.toString());
                            flag=true;
                        }
                    }
                    while(true) {
                        try {

                            Thread.sleep(5000);
                            Date date = new Date();
                            Timestamp timestamp = new Timestamp(date.getTime());


                            ResultSet set = statement.executeQuery("select count(*) from VISITOR where ADMIN_ID=" + admin_id + " and hasmeet is null and HASACCEPTED is null and timestampdiff(MINUTE,ENTRY,'" + timestamp + "')>20 and ENTRY BETWEEN '" + timestamp1 + "' and '" + timestamp + "'");
                            set.next();
                            int count = set.getInt(1);
                            set.close();
                            if (count != 0) {
                                notification("Admin, " + a_username,"There Are Some Visitor Request Has Been Waiting For 20 Minutes ");
                                Thread.sleep(500000);

                            }
                            else
                            {
                                Thread.sleep(1000);
                            }
                            Log.e("admin", "While -Admin  Done");
                        } catch (Exception e) {
                            Log.e("admin", "While -Admin " + e.toString());


                        }
                    }
                }
            }).start();
        }
            Cursor s2 = d.fetch("teacher");
        if(s2.getCount()!=0)
        {
            s2.moveToFirst();
            t_username=s2.getString(0);
           new Thread( new Runnable(){
            @Override
            public void run ()
            {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                c=new connection();
                statement=c.get();
                try {
                    Thread.sleep(8000);
                }
                catch (Exception e){}
                boolean flag=true;
                while (flag) {
                    try {
                        ResultSet set = statement.executeQuery("select ID FROM TEACHER WHERE USERNAME='" + t_username + "'");
                        set.next();
                        t_id = set.getInt(1);
                        set.close();
                        flag=false;
                    } catch (Exception e) {
                        Log.e("admin", "Outer_Teacher " + e.toString());
                        flag=true;
                    }
                }

                while(true) {
                    try {
                        Thread.sleep(3000);
                        Date today = new Date();
                        Timestamp t = new Timestamp(today.getTime());
                        String timestamp = t.toString();
                        String array[] = timestamp.split(" ");
                        timestamp = array[0] + " 00:00:01 ";

                        Date date = new Date();
                        Timestamp timestamp1 = new Timestamp(date.getTime());
                        ResultSet set = statement.executeQuery("select count(*) from VISITOR where TID=" + t_id + " and hasmeet is null and HASACCEPTED IS NULL and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp1 + "'");
                        set.next();
                        int count = set.getInt(1);
                        set.close();
                        if (count != 0) {
                            notification("Teacher," + t_username,"Some Visitor Request To You");
                            Thread.sleep(500000);
                        }
                        else
                        {
                            Thread.sleep(1000);
                        }

                        Log.e("admin", "While -Teacher  Done");

                    } catch (Exception e) {
                        Log.e("admin", "While-Teacher " + e.toString());

                    }
                }

            }
            }).start();
        }
        Cursor s3=d.fetch("guard");
        if(s3.getCount()!=0)
        {
            s3.moveToFirst();
            g_username=s3.getString(0);
           new Thread( new Runnable(){
                @Override
                public void run ()
                {
                    Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                    c=new connection();
                    statement=c.get();

                    int previous_count=0;
                    Date today=new Date();
                    Timestamp t=new Timestamp(today.getTime());
                    String timestamp=t.toString();
                    String array[]=timestamp.split(" ");
                    timestamp=array[0]+" 00:00:01 ";
                    Timestamp timestamp11=new Timestamp(today.getTime());
                    boolean flag=true;
                    while (flag) {
                        try {
                            ResultSet set1 = statement.executeQuery("select ADMIN_ID FROM GUARD WHERE USERNAME='" + g_username + "'");
                            set1.next();
                            g_admin_id = set1.getInt(1);
                            set1.close();

                            ResultSet set = statement.executeQuery("select count(*) from VISITOR where ADMIN_ID=" + g_admin_id + " and hasmeet is null and HASACCEPTED IS NOT NULL and ENTRY BETWEEN '" + timestamp + "' and '" + timestamp11 + "'");
                            set.next();
                            previous_count = set.getInt(1);
                            set.close();
                            flag=false;
                        }
                        catch (Exception e) {
                            Log.e("admin", "Outer_Guard " + e.toString());
                            flag=true;


                        }
                    }
                    try
                    {
                        Thread.sleep(6000);
                    }
                    catch (Exception e)
                    {
                    }
                    while (true) {

                        Date date=new Date();
                        Timestamp t1=new Timestamp(date.getTime());
                        String timestamp111=t1.toString();
                        String array1[]=timestamp111.split(" ");
                        timestamp111=array1[0]+" 00:00:01 ";
                        Timestamp timestamp1=new Timestamp(date.getTime());


                        try {
                            if(statement==null)
                            {
                                c.establish();
                                statement=c.get();
                            }
                            Thread.sleep(1000);
                                ResultSet set=null;
                                int count=0;
                            set = statement.executeQuery("select count(*) from VISITOR where ADMIN_ID=" + g_admin_id + " and hasmeet is null and HASACCEPTED IS NOT NULL and ENTRY BETWEEN '" + timestamp111 + "' and '" + timestamp1 + "'");
                            if(set!=null) {
                                set.next();
                                count = set.getInt(1);
                                set.close();
                            }
                            if (count != 0) {
                                if (previous_count < count) {


                                    notification("Guard, " + g_username,"Some Visitors Request Has Been Accepted");

                                    previous_count = count;
                                }
                                else
                                {
                                    Thread.sleep(10000);
                                }

                            }
                            else
                            {
                                Thread.sleep(10000);
                            }
                            Log.e("admin", "While -Guard  Done");

                        } catch (Exception e) {

                            Log.e("admin", " While-Guard " + e.toString());

                        }
                    }

                }
            }).start();
        }
        }
        catch (Exception e)
        {
            Log.e("admin","Start "+e.toString());
        }
        return START_STICKY;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("admin", "serviceOnDestroy()");
        Intent broadcastIntent = new Intent("Restart");
        sendBroadcast(broadcastIntent);
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Log.i("admin", "serviceonTaskRemoved()");
        Intent broadcastIntent = new Intent("Restart");
        sendBroadcast(broadcastIntent);
        super.onTaskRemoved(rootIntent);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
