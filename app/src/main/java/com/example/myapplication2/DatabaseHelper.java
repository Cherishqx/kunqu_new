package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";
    private static final int DATABASE_VERSION = 1;
     private static final int oldVersion=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        // 创建 collections 表
        MyDatabase.execSQL("create Table collections(Id INTEGER primary key, content TEXT)");

        // 检查 collections 表中是否已有数据
        Cursor cursor = MyDatabase.rawQuery("SELECT COUNT(*) FROM collections", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count == 0) {
                // 如果表中没有数据，则插入内置数据
                MyDatabase.execSQL("insert into collections (Id, content) values (1, 'Initial Content 1')");
                MyDatabase.execSQL("insert into collections (Id, content) values (2, 'Initial Content 2')");
                MyDatabase.execSQL("insert into collections (Id, content) values (3, 'Initial Content 3')");
                MyDatabase.execSQL("insert into collections (Id, content) values (4, 'Initial Content 4')");
                MyDatabase.execSQL("insert into collections (Id, content) values (5, 'Initial Content 5')");
                MyDatabase.execSQL("insert into collections (Id, content) values (6, 'Initial Content 6')");
                MyDatabase.execSQL("insert into collections (Id, content) values (7, 'Initial Content 7')");
                MyDatabase.execSQL("insert into collections (Id, content) values (8, 'Initi12al Content 8')");
            }
            cursor.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion , int DATABASE_VERSION ) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists collections");
        onCreate(MyDatabase);
    }

    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
//        contentValues.put
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 插入数据到 collections 表
    public Boolean insertCollection(int Id, String content) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", Id);
        contentValues.put("content", content);
        long result = MyDatabase.insert("collections", null, contentValues);

        return result != -1;
    }

    // 查询指定 Id 的 collection 数据
    public Cursor getCollectionById(int Id) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        return MyDatabase.rawQuery("SELECT * FROM collections WHERE Id = ?", new String[]{String.valueOf(Id)});
    }

    // 查询所有 collections 数据
    public Cursor getAllCollections() {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        return MyDatabase.rawQuery("SELECT * FROM collections", null);
    }

    // 更新指定 Id 的 collection 数据
    public Boolean updateCollection(int Id, String content) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("content", content);

        int result = MyDatabase.update("collections", contentValues, "Id = ?", new String[]{String.valueOf(Id)});
        return result > 0;
    }

    // 删除指定 Id 的 collection 数据
    public Boolean deleteCollection(int Id) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        int result = MyDatabase.delete("collections", "Id = ?", new String[]{String.valueOf(Id)});
        return result > 0;
    }
}
