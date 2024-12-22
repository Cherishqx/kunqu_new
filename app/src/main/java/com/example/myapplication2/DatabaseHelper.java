package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication2.Data.History;
import com.example.myapplication2.Data.TicketInformation;
import com.example.myapplication2.Data.Sentence;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "sentences";
    public static final String COLUMN_ID = "Id";//建立常量，方便
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_INTRO= "intro";
    public static final String COLUMN_PERID = "perID";
    public static final String COLUMN_NAME= "name";
    public static final String COLUMN_TIME= "time";
    public static final String COLUMN_PLACE= "place";
    public static final String COLUMN_FARE= "fare";
    public static final String COLUMN_picNAME= "picName";


    public static final String databaseName = "Signup.db";
    private static final int DATABASE_VERSION = 1;
//     private static final int oldVersion=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        // 创建 collections,history,ticketinformation 表
        MyDatabase.execSQL("create Table sentences(Id INTEGER primary key, content TEXT)");
        MyDatabase.execSQL("create Table history(title primary key, intro TEXT)");
        MyDatabase.execSQL("create Table ticketinformation(perID primary key, name TEXT,time TEXT,place TEXT,fare TEXT,picName TEXT)");

        // 检查 collections 表中是否已有数据
        Cursor cursor = MyDatabase.rawQuery("SELECT COUNT(*) FROM sentences", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count == 0) {
                // 如果表中没有数据，则插入内置数据
                MyDatabase.execSQL("insert into sentences (Id, content) values (1, '情不知所起，一往而深，\n" +
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

        // 检查 history表中是否已有数据
        Cursor cursor2 = MyDatabase.rawQuery("SELECT COUNT(*) FROM history", null);
        if (cursor2 != null) {
            cursor2.moveToFirst();
            int count = cursor2.getInt(0);
            if (count == 0) {
                // 如果表中没有数据，则插入内置数据
                MyDatabase.execSQL("insert into history (title, intro) values ('南宋光宗时期', '\t\t浙江永嘉南戏崛起，以南方民间音乐为演唱曲调，演出自由活泼，但存在缺陷，长期处于较低层次。南戏在发展中不断吸收传统音乐形式，逐渐丰富。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('金代和元代', '\t\t北方杂剧兴起，南方戏文起源于建炎南渡前后。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('元末', '\t\t昆山顾坚推动昆山腔发展，顾坚精于南辞，善作古赋，其居住地昆山的南戏与当地民间曲调结合形成昆山腔，为昆曲前身，但此时昆山腔仅为清唱音乐形式，在苏州一带流行。\n" +
                        "\t\t高明编写的南戏剧本《琵琶记》受朱元璋称赏，在宫廷排演，南戏开始向高雅艺术境界迈进。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('明代中叶以前', '\t\t昆山腔传播范围有限，主要在苏州地区流行，苏州的经济繁荣带动文化艺术发展，为昆曲走向中心舞台奠定基础。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('明代嘉靖、隆庆年间', '\t\t魏良辅对昆山腔进行全面改革，汇集南北曲调优长，借鉴江南民歌小调，整合出新式曲调，演唱时注意字音与曲调配合，延长音节，形成舒缓节奏，即流传后世的昆曲。同时，张野塘协助魏良辅将北方曲调融入昆曲伴奏，改造三弦，使昆曲唱腔委婉细腻，被称为 “水磨腔”。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('明代万历年间', '\t\t梁辰鱼创作《浣纱记》，最早用昆曲形式演出，昆曲影响扩大，传播到江苏、浙江广大地区，成为主要戏剧形式。\n" +
                        "\t\t汤显祖诞生，其创作的《牡丹亭》大胆展现闺门少女爱情幻梦，突破传统伦理道德中情与理的冲突，追求理想爱情观，引起巨大轰动。\n" +
                        "\t\t沈璟创作注重戏剧艺术特性，其《义侠记》取材《水浒传》武松故事，肯定武松正义行为，维护社会秩序，他带动形成吴江派作家群。此时，政治和爱情成为昆曲剧作两大主题。')");

                MyDatabase.execSQL("insert into history (title, intro) values ('明代晚期','\t\t昆曲创作进入全盛时期，名家辈出，产生大量著名剧作，包括吸收改造元代剧作。昆曲演出兴盛，民间职业戏班和家庭昆曲戏班众多，业余演员登台表演也成为重要组成部分。同时，昆曲演出进入宫廷，在北方广泛传播。\n" +
                        "\t\t福建昆曲在万历年间前已传入，福州有公开演出，官宦人家自蓄家班，昆剧剧本在闽北出版发行，对福建地方剧种产生影响，如儒林戏 “逗腔” 包含昆山腔成分，梨园戏、词明戏、四平戏、南词戏等也搬演过昆剧剧目，民间还有坐唱形式。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('明末清初','\t\t苏州派剧作家兴起，关注现实，作品反映政治图景和市民思想生活，艺术上超越昆曲传统美学，显示宏大叙事风格，李玉成就最大，创作众多优秀昆曲作品。\n" +
                        "\t\t李渔创作十个昆曲剧本，撰写重要戏剧理论著作《闲情偶寄》，其代表作《风筝误》是幽默风俗喜剧，思想平庸但技巧纯熟，舞台演出效果好。')");

                MyDatabase.execSQL("insert into history (title, intro) values ('清代康熙年间','\t\t洪昇的《长生殿》和孔尚任的《桃花扇》相继问世，标志新一轮昆曲创作高潮到来。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('清代中叶','\t\t昆曲走向衰微，后继者创作水平下降，作品缺乏新意，艺术上远离大众欣赏趣味，同时清政府禁令使家庭昆曲剧团消失，失去社会基础。\n" +
                        "t\t花部兴起，地方戏曲以其粗犷格调、旺盛生命力和丰富形式冲击昆曲，昆曲逐渐退出主流舞台，但昆曲艺术元素被地方戏剧吸收，促成地方戏剧繁荣和京剧诞生。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('清代中叶以后','\t\t为与地方戏剧竞争，昆曲戏班改革，产生 “折子戏”，编创通俗短剧和武戏，昆曲凭借这些形式重新激发艺术生机。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('20世纪50年代','\t\t浙江昆苏剧团改编的《十五贯》演出成功，轰动全国，各地相继成立昆曲院团，昆曲迎来复兴。\n" +
                        "\t\t众多老一辈表演艺术家及解放后培养的演员整理编演大量优秀剧目。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('2000年','\t\t中国昆剧艺术节创办，宗旨为保存和发展昆剧艺术，增进世界各国爱好者沟通，活动包括剧目评选、展览、演出、研讨会等。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('2001年','\t\t昆曲入选联合国首批 “人类口头非物质遗产代表作”。')");
                MyDatabase.execSQL("insert into history (title, intro) values ('2019-2023年','\t\t相关单位获得 “昆曲” 项目保护单位资格并通过评估。')");


            }
            cursor2.close();
        }

        // 检查 ticketinformation表中是否已有数据
        Cursor cursor1 = MyDatabase.rawQuery("SELECT COUNT(*) FROM ticketinformation", null);
        if (cursor1 != null) {
            cursor1.moveToFirst();
            int count = cursor1.getInt(0);
            if (count == 0) {
                // 如果表中没有数据，则插入内置数据
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (1, '《西厢记》', '12月6日 周五 19:30', '北京・天桥艺术中心・中剧场', '¥80起', 'ticket1')");
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (2, '《牡丹亭》', '12月13日-15日', '上海・上海大剧院・别克中剧场', '¥80起', 'ticket2')");
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (3, '《牡丹亭》', '12月22日 周日 19:30', '北京・天桥艺术中心・中剧场', '¥80起', 'ticket3')");
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (4, '现代昆曲《李佩先生》', '12月25日-27日 19:30', '北京・长安大戏院', '¥50起', 'ticket4')");
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (5, '《牡丹亭》全本・精华版 甲辰年封箱演出', '1月1日 周二 19:30', '昆明・昆明剧院・大剧场', '¥120起', 'ticket8')");
                MyDatabase.execSQL("insert into ticketinformation (perID, name, time, place, fare, picName) values (6, '戏曲展演《游园惊梦》', '02月15日-16日', '北京・国家大剧院・戏剧场', '¥80起', 'ticket9')");

            }
            cursor1.close();
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

        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        long insert=MyDatabase.insert(TABLE_NAME,COLUMN_CONTENT,cv);
        if(insert==-1){
            return "fail";
        }
        MyDatabase.close();//"getWritableDatabase"打开了，此处用完关闭
        return"succese";
    }

    public String deleteOne(Sentence sentence){
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        int delete = MyDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(sentence.getId())});
        if(delete==0){
            return "fail";
        }

        MyDatabase.close();//"getWritableDatabase"打开了，此处用完关闭
        return"succese";
    }

    public String updateOne(Sentence sentence){
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CONTENT,sentence.getContent());

        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        int update = MyDatabase.update(TABLE_NAME, cv, COLUMN_CONTENT + "=?", new String[]{String.valueOf(sentence.getId())});
        if(update==0){
            return "fali";
        }
        MyDatabase.close();//"getWritableDatabase"打开了，此处用完关闭
        return"succese";
    }

    public List<Sentence> getAll() {
        Sentence sentence;
        List<Sentence> list = new ArrayList<>();

        // 按照 ID 倒序排序
        String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery(sql, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int contentIndex = cursor.getColumnIndex(COLUMN_CONTENT);

        while (cursor.moveToNext()) {
            sentence = new Sentence(cursor.getInt(idIndex), cursor.getString(contentIndex));
            list.add(sentence);
        }

        cursor.close();
        MyDatabase.close();
        return list;
    }


    public List<History> getHistoryAll(){
        History history;
        List<History> list=new ArrayList<>();
        String sql="SELECT * FROM "+"history";

        SQLiteDatabase MyDatabase=this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery(sql, null);

        int titleIndex=cursor.getColumnIndex(COLUMN_TITLE);
        int introIndex= cursor.getColumnIndex(COLUMN_INTRO);

        while (cursor.moveToNext()){
            history=new History(cursor.getString(titleIndex),cursor.getString(introIndex));
            list.add(history);
        }
        cursor.close();
        MyDatabase.close();
        return list;
    }

    public List<TicketInformation> getTicketAll(){
        TicketInformation ticketinformation;
        List<TicketInformation> list=new ArrayList<>();
        String sql="SELECT * FROM "+"ticketinformation";

        SQLiteDatabase MyDatabase=this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery(sql, null);

        int perIdIndex=cursor.getColumnIndex(COLUMN_PERID);
        int nameIndex= cursor.getColumnIndex(COLUMN_NAME);
        int timeIndex= cursor.getColumnIndex(COLUMN_TIME);
        int placeIndex= cursor.getColumnIndex(COLUMN_PLACE);
        int fareIndex= cursor.getColumnIndex(COLUMN_FARE);
        int picnameIndex= cursor.getColumnIndex(COLUMN_picNAME);

        while (cursor.moveToNext()){
            ticketinformation=new TicketInformation(cursor.getString(perIdIndex),cursor.getString(nameIndex),cursor.getString(timeIndex),cursor.getString(placeIndex),cursor.getString(fareIndex),cursor.getString(picnameIndex));
            list.add(ticketinformation);
        }
        cursor.close();
        MyDatabase.close();
        return list;
    }

}
