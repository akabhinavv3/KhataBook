package com.example.khatabook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "KhataBook.db";
    private static final int DATABASE_VERSION = Integer.parseInt("1");
    private static final String TABLE_NAME = "my_khatabook";
    private static final String COLUMN_ID = "id";
    private static final String DATE = "added_date";
    private static final String BALANCE = "total_bal";
    private static final String DETAILS = "details";
//    private static final String GAVE = "you_gave";
//    private static final String GOT = "you_got";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                         " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         DATE + " TEXT, " +
                        BALANCE + " INTEGER, " +
                        DETAILS + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addDetails(String date, int balance, String details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DATE, date);
        cv.put(BALANCE, balance);
        cv.put(DETAILS, details);
//        cv.put(GAVE, gave);
//        cv.put(GOT, got);
        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added sucessfully", Toast.LENGTH_SHORT).show();
        }
    }

    List<Model> readAllData(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();

/*
        Cursor cursor = null;
        if (db !=null){
            db.rawQuery(query,null);

        }
        return cursor;
*/



        List<Model> contactList = new ArrayList<>();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Model contact = new Model();
                contact.setDate((cursor.getString(1)));
                contact.setAmount(cursor.getString(2));
                contact.setAmountDetails(cursor.getString(3));
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }
}
