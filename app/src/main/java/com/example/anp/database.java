package com.example.anp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public  database(Context e)
    {
        super(e,"ACTIVE",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase d)
    {
    d.execSQL("create table active(username varchar(15) not null,password varchar(15) not null,isactive int default 0,type varchar(9) not null)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase d,int old,int newV)
    {
        d.execSQL("drop table if exists active");
        onCreate(d);
    }
    public void Delete(String username,String type)
    {
        SQLiteDatabase d=getWritableDatabase();
        d.execSQL("delete from active where username='"+username+"'and type='"+type+"'");
    }
    public Cursor fetch(String type)
    {
        SQLiteDatabase d=getReadableDatabase();
      Cursor c=  d.query("active",null,"type=?",new String[]{type},null,null,null);
        return c;
    }
    public void insert(String username,String password,String type)
    {
        ContentValues value=new ContentValues();
        value.put("username",username);
        value.put("password",password);
        value.put("isactive",1);
        value.put("type",type);
        SQLiteDatabase d=getWritableDatabase();
        d.insert("active",null,value);

    }


}
