package com.example.lab12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "items.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DIRECTOR = "director";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "director TEXT NOT NULL)";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertItem(String name, String director) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DIRECTOR, director);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void fillTable() {
        insertItem("Амели", "Жан-Пьер Жёне");
        insertItem("Назад в будущее", "Роберт Земекис");
        insertItem("Криминальное чтиво", "Квентин Тарантино");
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String director = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIRECTOR));
                itemList.add(new Item(id, name, director));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return itemList;
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM items";
        db.execSQL(sql);
    }
}
