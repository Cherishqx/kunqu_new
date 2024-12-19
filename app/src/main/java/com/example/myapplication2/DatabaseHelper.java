package com.example.myapplication2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication2.Data.Sentence;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "sentences";
    public static final String COLUMN_ID = "Id";//建立常量，方便
    public static final String COLUMN_CONTENT = "content";

    public static final String databaseName = "Signup.db";
    private static final int DATABASE_VERSION = 1;
//     private static final int oldVersion=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        // 创建 collections 表
        MyDatabase.execSQL("create Table sentences(Id INTEGER primary key, content TEXT)");

        // 检查 collections 表中是否已有数据
        Cursor cursor = MyDatabase.rawQuery("SELECT COUNT(*) FROM sentences", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count == 0) {
                // 如果表中没有数据，则插入内置数据
                MyDatabase.execSQL("insert into sentences (Id, content) values (1, '情不知所起， 一往而深，\n" +
                        "生者可以死，死可以生。 \n" +
                        "        ——《牡丹亭》')");
                MyDatabase.execSQL("insert into sentences (Id, content) values (2, '此生终老温柔，白云不羡仙乡，\n惟愿取恩情美满，地久天长。\n    ————《长生殿》')");
                MyDatabase.execSQL("insert into sentences (Id, content) values (3, '长梦不多时，短梦无碑记。\n普天下梦南柯人似蚁。\n    ————《南柯记》')");
                MyDatabase.execSQL("insert into sentences (Id, content) values (4, '满天涯烟草断人肠怕催花信紧，\n风风雨雨，误了春光。\n    ————《桃花扇》')");
                MyDatabase.execSQL("insert into sentences (Id, content) values (5, '北雁南飞。晓来谁染霜林醉？\n总是离人泪。\n    ————《西厢记》')");
                MyDatabase.execSQL("insert into sentences (Id, content) values (6, '誓海盟山永不移，\n从今孽债染缁衣。\n    ————《玉簪记》')");
            }
            cursor.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion , int DATABASE_VERSION ) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists sentences");
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


    public String addOne(Sentence sentence){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CONTENT,sentence.getContent());

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long insert=sqLiteDatabase.insert(TABLE_NAME,COLUMN_CONTENT,cv);
        if(insert==-1){
            return "fail";
        }
        sqLiteDatabase.close();//"getWritableDatabase"打开了，此处是用完关闭
        return"succese";
    }

    public String deleteOne(Sentence sentence){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(sentence.getId())});
        if(delete==0){
            return "fail";
        }

        sqLiteDatabase.close();//"getWritableDatabase"打开了，此处是用完关闭
        return"succese";
    }

    public String updateOne(Sentence sentence){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CONTENT,sentence.getContent());

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int update = sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_CONTENT + "=?", new String[]{String.valueOf(sentence.getId())});
        if(update==0){
            return "fali";
        }
        sqLiteDatabase.close();//"getWritableDatabase"打开了，此处是用完关闭
        return"succese";
    }

    public List<Sentence> getAll() {
        Sentence sentence;
        List<Sentence> list = new ArrayList<>();

        // 按照 ID 倒序排序
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int contentIndex = cursor.getColumnIndex(COLUMN_CONTENT);

        while (cursor.moveToNext()) {
            sentence = new Sentence(cursor.getInt(idIndex), cursor.getString(contentIndex));
            list.add(sentence);
        }

        cursor.close();
        sqLiteDatabase.close();
        return list;
    }



//    // 插入数据到 collections 表，并返回新的插入的 Id
//    public int insertCollection(String content) {
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//
//        // 查询最大 Id
//        Cursor cursor = MyDatabase.rawQuery("SELECT MAX(Id) FROM collections", null);
//        int newId = 1; // 默认从 1 开始，如果表为空，则 newId 为 1
//
//        if (cursor != null && cursor.moveToFirst()) {
//            // 获取当前最大 Id，如果有数据就加 1，否则新插入的记录将使用 Id = 1
//            newId = cursor.getInt(0) + 1;
//            cursor.close();
//        }
//
//        // 插入新数据
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Id", newId);
//        contentValues.put("content", content);
//
//        long result = MyDatabase.insert("collections", null, contentValues);
//
//        // 如果插入失败，则返回 -1，否则返回新插入数据的 Id
//        return result != -1 ? newId : -1;
//    }
//
//
//    // 查询所有 collections 数据
//    public Cursor getAllCollections() {
//        SQLiteDatabase MyDatabase = this.getReadableDatabase();
//        return MyDatabase.rawQuery("SELECT * FROM collections", null);
//    }



}
