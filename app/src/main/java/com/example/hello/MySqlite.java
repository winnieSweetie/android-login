package com.example.hello;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class MySqlite extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public MySqlite(Context context)
    {
        super(context,"db_test",null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users( userName TEXT PRIMARY KEY NOT NULL, password TEXT NOT NULL, email TEXT UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
    public void insert(String name,String password )
    {
        db.execSQL("INSERT INTO users(userName,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void demo()
    {
        db.execSQL("SELECT * From users", null);
    }
    public void updatePassword(String userName1, String newPassword)
    {
        db.execSQL("UPDATE users SET password = ? WHERE userName = ?", new Object[]{newPassword,userName1});
    }
    public void updateEmail(String userName1, String newEmail)
    {
        db.execSQL("UPDATE users SET email = ? WHERE userName = ?", new Object[]{newEmail,userName1});
    }
    public Boolean checkExist(String name)
    {
        Cursor cursor = db.rawQuery("SELECT userName FROM users WHERE userName = ?", new String[]{name});
        if(cursor!=null)
        {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public HashMap<String,String> getAllData()
    {
        HashMap<String,String> data = new HashMap<String,String>();
        Cursor cursor = db.query("users",new String[]{"userName","password"},null,null,null,null,"userName DESC");
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex("userName"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            if(!data.containsKey(name))
            {
                data.put(name, password);
            }
        }
        cursor.close();
        return data;
    }
}
