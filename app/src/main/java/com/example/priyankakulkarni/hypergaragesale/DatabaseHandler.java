package com.example.priyankakulkarni.hypergaragesale;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;


/**
 * Created by priyankakulkarni on 10/17/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "GarageSaleItems";

    //item table name
    private static final String TABLE_GARAGE_ITEM = "GarageItems";

    //Garage_Item column name
    private static final String KEY_ID = "id";
    private static final String KEY_ITEM = "Item";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_EMAILADDRESS = "emailAddress";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_GARAGE_ITEM + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ITEM + " TEXT,"
                + KEY_PRICE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_EMAILADDRESS + " TEXT " + ")";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GARAGE_ITEM);

        // Create tables again
        onCreate(db);
    }
    // Adding new item
    void addGarageItem(GarageItem garageItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // values.put(KEY_ID, 0);
        values.put(KEY_ITEM, garageItem.getItemName());
        values.put(KEY_PRICE, garageItem.getPrice());
        values.put(KEY_DESCRIPTION, garageItem.getDescription());
        values.put(KEY_EMAILADDRESS, garageItem.getEmalAdd());

        // Inserting Row
        db.insert(TABLE_GARAGE_ITEM, null, values);
        db.close(); // Closing database connection

    }

    // Getting All Contacts
    public ArrayList<GarageItem> getAllGarageItems() {
        ArrayList<GarageItem> garaItems = new ArrayList<GarageItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GARAGE_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GarageItem garageItem = new GarageItem();
                garageItem.setItemName(cursor.getString(1));
                garageItem.setPrice(Double.parseDouble(cursor.getString(2)));
                garageItem.setDescription(cursor.getString(3));
                garageItem.setEmalAdd(cursor.getString(4));

                // Adding contact to list
                garaItems.add(garageItem);
            } while (cursor.moveToNext());
        }

        // return contact list
        return garaItems;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GARAGE_ITEM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
