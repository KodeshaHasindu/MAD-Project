package com.example.bookinghotel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    //create database
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HotelProfile.Hotels.TABLE_NAME + " (" +
                    HotelProfile.Hotels._ID + " INTEGER PRIMARY KEY," +
                    HotelProfile.Hotels.COLUMN_1 + " TEXT," +
                    HotelProfile.Hotels.COLUMN_2 + " TEXT,"+
                    HotelProfile.Hotels.COLUMN_3 + " TEXT," +
                    HotelProfile.Hotels.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HotelProfile.Hotels.TABLE_NAME;

    //put information into tables
    public long addInfo(String hotel_name, String registration_number, String contact_number, String email_address ){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

    // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(HotelProfile.Hotels.COLUMN_1, hotel_name);
        values.put(HotelProfile.Hotels.COLUMN_2, registration_number);
        values.put(HotelProfile.Hotels.COLUMN_3, contact_number);
        values.put(HotelProfile.Hotels.COLUMN_4, email_address);


    // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HotelProfile.Hotels.TABLE_NAME, null, values);

        return newRowId;
    }

    //update INfo database
    public Boolean updateInfo(String hotel_name, String registration_number, String contact_number, String email_address ) {
        SQLiteDatabase db =getWritableDatabase();

    // New value for one column
        ContentValues values = new ContentValues();
        values.put(HotelProfile.Hotels.COLUMN_2, registration_number);
        values.put(HotelProfile.Hotels.COLUMN_3, contact_number);
        values.put(HotelProfile.Hotels.COLUMN_4, email_address);


        // Which row to update, based on the title
        String selection = HotelProfile.Hotels.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { hotel_name };

        //count gives database number of updated
        int count = db.update(
                HotelProfile.Hotels.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count>=1){
            return true;
        }
        else{
            return false;
        }

    }

    //delete info

    public void deleteInfo (String hotel_name){

        SQLiteDatabase db =getWritableDatabase();

        // Define 'where' part of query.
        String selection = HotelProfile.Hotels.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { hotel_name };
        // Issue SQL statement.
        int deletedRows = db.delete(HotelProfile.Hotels.TABLE_NAME, selection, selectionArgs);

    }
    //Read All infor
    public List readAllInfo(){

        String hotel_name= "himasha";
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                HotelProfile.Hotels.COLUMN_1,
                HotelProfile.Hotels.COLUMN_2,
                HotelProfile.Hotels.COLUMN_3,
                HotelProfile.Hotels.COLUMN_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = HotelProfile.Hotels.COLUMN_1 + " = ?";
        String[] selectionArgs = {hotel_name};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                HotelProfile.Hotels.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                HotelProfile.Hotels.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,
                null,           // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List hotelnames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String hotel = cursor.getString(cursor.getColumnIndexOrThrow(HotelProfile.Hotels.COLUMN_1));
            hotelnames.add(hotel);
        }
        cursor.close();
        return hotelnames;
    }


    public List readAllInfo(String hotel_name){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                HotelProfile.Hotels.COLUMN_1,
                HotelProfile.Hotels.COLUMN_2,
                HotelProfile.Hotels.COLUMN_3,
                HotelProfile.Hotels.COLUMN_4
        };

// Filter results WHERE "title" = 'My Title'
        String selection = HotelProfile.Hotels.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = {hotel_name};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                HotelProfile.Hotels.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                HotelProfile.Hotels.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,
                selectionArgs,           // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List hotelInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String hotel = cursor.getString(cursor.getColumnIndexOrThrow(HotelProfile.Hotels.COLUMN_1));
            String registration_number = cursor.getString(cursor.getColumnIndexOrThrow(HotelProfile.Hotels.COLUMN_2));
            String contact_number = cursor.getString(cursor.getColumnIndexOrThrow(HotelProfile.Hotels.COLUMN_3));
            String email_address = cursor.getString(cursor.getColumnIndexOrThrow(HotelProfile.Hotels.COLUMN_4));

            hotelInfo.add(hotel); //0 index
            hotelInfo.add(registration_number); //1 index
            hotelInfo.add(contact_number); //2 index
            hotelInfo.add(email_address); //3 index

        }
        cursor.close();
        return hotelInfo;
    }

}
