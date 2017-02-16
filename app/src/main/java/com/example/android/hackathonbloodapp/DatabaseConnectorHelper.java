package com.example.android.hackathonbloodapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar on 14-02-2017.
 */
public class DatabaseConnectorHelper extends SQLiteOpenHelper {


    public static final String CONTENT_AUTHORITY = "com.example.android.hackathonbloodapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BLOODS = "hackathonbloodapp";


    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BLOODS);




    private static final int Version =1;

    public static final String firstName="FIRST_NAME";
    public static final String YourPlace="YOUR_PLACE";
    public static final String phone="PHONE";
    public static final String whichBloodNeeded="WHICH_BLOOD_NEEDED";
    public static final String hospitalName="HOSPITAL_NAME";
    public static final String urgentOrVeryUrgent="URGENT_OR_VERY_URGENT";
    public static final String receiverorgiver="RECEIVERORGIVER";
    public static final String databaseName="FORM";
    public static final String tableName="USER_RECORDS";
    public static final String id = BaseColumns._ID;

    public DatabaseConnectorHelper(Context context) {
        super(context, databaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableSQL = "CREATE TABLE " + tableName + " (" + id +" INTEGER PRIMARY KEY AUTOINCREMENT, " + firstName +" TEXT, " + YourPlace
                + " TEXT, "  + phone + " TEXT, " + whichBloodNeeded + " TEXT, " + hospitalName + " TEXT, " + receiverorgiver + " TEXT, " + urgentOrVeryUrgent +" TEXT)";
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    public void addRecord(String firstname, String yourplace, String phonenumber, String whichbloodneeded, String hospitalname,String receiverorgiver1 , String urgentorveryurgent ) {
        String insertSQL = "INSERT INTO " + tableName + " (" + firstName + ", " + YourPlace + " ," + phone + " ," + whichBloodNeeded + " ," + hospitalName + "," + receiverorgiver + " ," + urgentOrVeryUrgent + ")"
                + "VALUES" + " ('" + firstname + "', '" + yourplace + "', '" + phonenumber + "', '" + whichbloodneeded + "', '" + hospitalname + "', '" + receiverorgiver1 + "', '" + urgentorveryurgent + "')" ;
        SQLiteDatabase dataBase = this.getWritableDatabase();
        dataBase.execSQL(insertSQL);
        dataBase.close();
    }



}
