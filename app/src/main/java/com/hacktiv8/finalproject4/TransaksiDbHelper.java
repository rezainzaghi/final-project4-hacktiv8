package com.hacktiv8.finalproject4;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TransaksiDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "transaksi.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "transaksi";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BUS_NAME = "bus_name";
    public static final String COLUMN_FROM = "selected_from";
    public static final String COLUMN_DESTINATION = "selected_destination";
    public static final String COLUMN_DATE = "selected_date";
    public static final String COLUMN_SEAT = "selected_seat";
    public static final String COLUMN_TOTAL = "total";
    public static final String COLUMN_PAYMENT_METHOD = "payment_method";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_BUS_NAME + " TEXT," +
                    COLUMN_FROM + " TEXT," +
                    COLUMN_DESTINATION + " TEXT," +
                    COLUMN_DATE + " TEXT," +
                    COLUMN_SEAT + " TEXT," +
                    COLUMN_TOTAL + " TEXT," +
                    COLUMN_PAYMENT_METHOD + " TEXT)";

    public TransaksiDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Tindakan yang diambil saat versi database diperbarui (misalnya, DROP TABLE)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
