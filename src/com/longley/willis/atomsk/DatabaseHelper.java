package com.longley.willis.atomsk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 2;
    private static final String ATOMSK_TABLE_NAME = "EnvironData";
    
    private static final String ATOMSK_TABLE_CREATE =
                "CREATE TABLE IF NOT EXISTS " + ATOMSK_TABLE_NAME + " (" +
                "Latitude" + " TEXT, " +
                "Longitude" + " TEXT, " +
                "DeviceName" + " TEXT, " +
                "DeviceMAC" + " TEXT, " +
                "DeviceRSSI" + " TEXT);";
	private static final String DATABASE_NAME = "ATOMSK";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ATOMSK_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}
}
