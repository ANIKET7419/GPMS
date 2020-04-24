package com.example.anp;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connection {

    static Statement statement=null;
    Connection connection=null;
    String error="";
    public void establish()
    {
        try {

            StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            String user = "mYif5IQQJS";
            String password = "4u8UKFgUcA";
            final  String url = "jdbc:mysql://remotemysql.com:3306/mYif5IQQJS";
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        }
        catch (Exception e)
        {
            error=e.toString();
        }
    }
  public Statement get()
    {
        if(!error.equals(""))
            Log.e("error",error);
        return statement;
    }
}
