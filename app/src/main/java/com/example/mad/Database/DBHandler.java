package com.example.mad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler<Public> extends SQLiteOpenHelper {
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

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_3 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_4 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_5 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_6 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    public long addInfo (String OwnerName, String contactNumber, String Address, String vehicleType, String vehicleModel, String passengers){
        // Gets the data repository in write mode
        SQLiteDatabase db =getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_1, OwnerName);
        values.put(FeedReaderContract.FeedEntry.COLUMN_2, contactNumber);
        values.put(FeedReaderContract.FeedEntry.COLUMN_3, Address);
        values.put(FeedReaderContract.FeedEntry.COLUMN_4, vehicleType);
        values.put(FeedReaderContract.FeedEntry.COLUMN_5, vehicleModel);
        values.put(FeedReaderContract.FeedEntry.COLUMN_6, passengers);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public Boolean updateInfo  (String OwnerName, String contactNumber, String Address, String vehicleType, String vehicleModel, String passengers){

        SQLiteDatabase db =getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_2, contactNumber);
        values.put(FeedReaderContract.FeedEntry.COLUMN_3, Address);
        values.put(FeedReaderContract.FeedEntry.COLUMN_4, vehicleType);
        values.put(FeedReaderContract.FeedEntry.COLUMN_5, vehicleModel);
        values.put(FeedReaderContract.FeedEntry.COLUMN_6, passengers);


        // Which row to update, based on the title
        String selection = FeedReaderContract.FeedEntry.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { OwnerName };

        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count >=1 ){
            return true;
        }
        else{
            return false;
        }
    }

        public void deleteInfo(String OwnerName){

            SQLiteDatabase db =getWritableDatabase();

        // Define 'where' part of query.
            String selection = FeedReaderContract.FeedEntry.COLUMN_1 + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { OwnerName };
            // Issue SQL statement.
            int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
        }

        public List readAllInfo() {
            String OwnerName = "kodesha";
            SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
            String[] projection = {
                    BaseColumns._ID,
                    FeedReaderContract.FeedEntry.COLUMN_1,
                    FeedReaderContract.FeedEntry.COLUMN_2,
                    FeedReaderContract.FeedEntry.COLUMN_3,
                    FeedReaderContract.FeedEntry.COLUMN_4,
                    FeedReaderContract.FeedEntry.COLUMN_5,
                    FeedReaderContract.FeedEntry.COLUMN_6,

            };

        // Filter results WHERE "title" = 'My Title'
            String selection = FeedReaderContract.FeedEntry.COLUMN_1 + " = ?";
            String[] selectionArgs = { OwnerName };

        // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    FeedReaderContract.FeedEntry.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    null,              // The columns for the WHERE clause
                    null,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            List OwnerNames = new ArrayList<>();
            while(cursor.moveToNext()) {
                String user= cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_1));
                OwnerNames.add(user);
            }
            cursor.close();
            return OwnerNames;

        }

    public List readAllInfo(String OwnerNames) {

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_1,
                FeedReaderContract.FeedEntry.COLUMN_2,
                FeedReaderContract.FeedEntry.COLUMN_3,
                FeedReaderContract.FeedEntry.COLUMN_4,
                FeedReaderContract.FeedEntry.COLUMN_5,
                FeedReaderContract.FeedEntry.COLUMN_6,

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_1 + " Like = ?";
        String[] selectionArgs = { OwnerNames };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String OwnerName= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_1));
            String contactNumber= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_2));
            String Address= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_3));
            String vehicleType= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_4));
            String vehicleModel= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_5));
            String passengers= cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_6));
            userinfo.add(OwnerName);
            userinfo.add(contactNumber);
            userinfo.add(Address);
            userinfo.add(vehicleType);
            userinfo.add(vehicleModel);
            userinfo.add(passengers);

        }
        cursor.close();
        return userinfo;

    }

}
