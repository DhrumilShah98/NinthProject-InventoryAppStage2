package com.example.dhrumilshah.inventoryappstage2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.dhrumilshah.inventoryappstage2.data.BooksBoxContract.BooksBoxEntry;

public class BooksBoxDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "books_box.db";

    public BooksBoxDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE " + BooksBoxEntry.TABLE_NAME + " ("
                + BooksBoxEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BooksBoxEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + BooksBoxEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + BooksBoxEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL, "
                + BooksBoxEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BooksBoxEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
