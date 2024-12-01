package com.example.myapplication2;

import android.annotation.SuppressLint;
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
                MyDatabase.execSQL("insert into collections (Id, content) values (1, '情不知所起， 一往而深，\n" +
                        "生者可以死，死可以生。 \n" +
                        "        ——《牡丹亭》')");
                MyDatabase.execSQL("insert into collections (Id, content) values (2, '此生终老温柔，白云不羡仙乡，\n惟愿取恩情美满，地久天长。\n    ————《长生殿》')");
                MyDatabase.execSQL("insert into collections (Id, content) values (3, '长梦不多时，短梦无碑记。普天下梦南柯人似蚁。\n    ————《南柯记》')");
                MyDatabase.execSQL("insert into collections (Id, content) values (4, '满天涯烟草断人肠怕催花信紧，\n风风雨雨，误了春光。\n    ————《桃花扇》')");
                MyDatabase.execSQL("insert into collections (Id, content) values (5, '北雁南飞。晓来谁染霜林醉？总是离人泪。\n    ————《西厢记》')");
                MyDatabase.execSQL("insert into collections (Id, content) values (6, '誓海盟山永不移，\n从今孽债染缁衣。\n    ————《玉簪记》')");
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

    // 插入数据到 collections 表，并返回新的插入的 Id
    public int insertCollection(String content) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        // 查询最大 Id
        Cursor cursor = MyDatabase.rawQuery("SELECT MAX(Id) FROM collections", null);
        int newId = 1; // 默认从 1 开始，如果表为空，则 newId 为 1

        if (cursor != null && cursor.moveToFirst()) {
            // 获取当前最大 Id，如果有数据就加 1，否则新插入的记录将使用 Id = 1
            newId = cursor.getInt(0) + 1;
            cursor.close();
        }

        // 插入新数据
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", newId);
        contentValues.put("content", content);

        long result = MyDatabase.insert("collections", null, contentValues);

        // 如果插入失败，则返回 -1，否则返回新插入数据的 Id
        return result != -1 ? newId : -1;
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

    public Boolean updateAllIds() {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();

        // 更新所有数据的 Id，按原来的顺序递增
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM collections ORDER BY Id DESC", null);
        int index = 1; // 从 1 开始递增
        boolean success = true;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int currentId = cursor.getInt(cursor.getColumnIndex("Id"));
                ContentValues contentValues = new ContentValues();
                contentValues.put("Id", index);
                int result = MyDatabase.update("collections", contentValues, "Id = ?", new String[]{String.valueOf(currentId)});
                if (result == 0) {
                    success = false;
                    break;
                }
                index++;
            } while (cursor.moveToNext());
            cursor.close();
        }

        return success;
    }

}
