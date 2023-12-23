package com.hacktiv8.finalproject4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TransaksiDataSource {
    private SQLiteDatabase database;
    private TransaksiDbHelper dbHelper;

    public TransaksiDataSource(Context context) {
        dbHelper = new TransaksiDbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertTransaksi(String busName, String selectedFrom, String selectedDestination, String selectedDate, String selectedSeat, String total, String paymentMethod) {
        ContentValues values = new ContentValues();
        values.put(TransaksiDbHelper.COLUMN_BUS_NAME, busName);
        values.put(TransaksiDbHelper.COLUMN_FROM, selectedFrom);
        values.put(TransaksiDbHelper.COLUMN_DESTINATION, selectedDestination);
        values.put(TransaksiDbHelper.COLUMN_DATE, selectedDate);
        values.put(TransaksiDbHelper.COLUMN_SEAT, selectedSeat);
        values.put(TransaksiDbHelper.COLUMN_TOTAL, total); // Menggunakan tipe data TEXT
        values.put(TransaksiDbHelper.COLUMN_PAYMENT_METHOD, paymentMethod);

        return database.insert(TransaksiDbHelper.TABLE_NAME, null, values);
    }

    public Cursor getAllTransaksi() {
        String[] allColumns = {
                TransaksiDbHelper.COLUMN_ID,
                TransaksiDbHelper.COLUMN_BUS_NAME,
                TransaksiDbHelper.COLUMN_FROM,
                TransaksiDbHelper.COLUMN_DESTINATION,
                TransaksiDbHelper.COLUMN_DATE,
                TransaksiDbHelper.COLUMN_SEAT,
                TransaksiDbHelper.COLUMN_TOTAL,
                TransaksiDbHelper.COLUMN_PAYMENT_METHOD
        };

        return database.query(
                TransaksiDbHelper.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null
        );
    }
}
