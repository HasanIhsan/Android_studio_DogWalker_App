package com.example.hassanihsan.project1dogwalker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "DogWalker_database3";
    //Database Table name
    private static final String TABLE_NAME = "DOGS";
    //Table columns
    public static final String ID = "id";
    public static final String DOGNAME = "dogname";
    public static final String DATE = "date";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + DOGNAME + " TEXT NOT NULL," +
            DATE+" TEXT NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    //upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add dog Data
    public void addDogs(DogModelClass dogModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.DOGNAME, dogModelClass.getName());
        contentValues.put(DatabaseHelperClass.DATE,dogModelClass.getDate());

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    //get list of dogs in database
    public List<DogModelClass> getDogsList(){
        String sql = "select * from " + TABLE_NAME;

        sqLiteDatabase = this.getReadableDatabase();
        List<DogModelClass> storedog= new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                System.out.println("Name" + name);
                System.out.println("date"+ date);
                storedog.add(new DogModelClass(id,name, date));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return storedog;
    }


    //delete a dog that user selects
    public void deleteDog(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    //detele all dogs
    public void delteAllDogs()
    {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME);
    }
}
