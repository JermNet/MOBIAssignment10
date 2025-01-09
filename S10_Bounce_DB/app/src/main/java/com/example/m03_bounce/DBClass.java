package com.example.m03_bounce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper implements DB_Interface {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 9;
    public static final String DATABASE_NAME = "Bounce_DB_Name.db";
    private static final String TABLE_NAME = "sample_table";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUM_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    // Names of columns
    private static final String _ID = "_ID";
    private static final String _COL_1 = "NAME";
    private static final String _COL_2 = "X";
    private static final String _COL_3 = "Y";
    private static final String _COL_4 = "DX";
    private static final String _COL_5 = "DY";
    private static final String _COL_6 = "COLOR";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "NAME VARCHAR(256), X FLOAT, Y FLOAT, DX FLOAT, DY FLOAT, COLOR INTEGER)";
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBClass", "DB onCreate() " + SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);
        Log.d("DBClass", "DB onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        Log.d("DBClass", "DB onUpgrade() to version " + DATABASE_VERSION);
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public int count() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        Log.v("DBClass", "getCount=" + cnt);
        return cnt;
    }

    // Save a dataModel to the DB, dump() was used here but has been deleted since that method is not implemented
    @Override
    public void save(DataModel dataModel) {
        Log.v("DBClass", "save()=>  " + dataModel.toString());

        // 1. Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(_COL_1, dataModel.getModelName());
        values.put(_COL_2, dataModel.getModelX());
        values.put(_COL_3, dataModel.getModelY());
        values.put(_COL_4, dataModel.getModelDX());
        values.put(_COL_5, dataModel.getModelDY());
        values.put(_COL_6, dataModel.getModelColor());

        // 3. Insert
        db.insert(TABLE_NAME, null, values);

        // 4.Close
        db.close();
    }

    // Find the balls
    @Override
    public List<DataModel> findAll() {
        List<DataModel> temp = new ArrayList<DataModel>();

        // 1. Build the query to get everything
        String query = "SELECT  * FROM " + TABLE_NAME;

        // 2. Get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. Go over each row, build and add it to list
        DataModel item;
        if (cursor.moveToFirst()) {
            do {
                item = new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getFloat(2),
                        cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5), cursor.getInt(6));
                temp.add(item);
            } while (cursor.moveToNext());
        }

        Log.v("DBClass", "findAll=> " + temp.toString());

        // Return all
        return temp;
    }

    // Intended to be used alongside the clearBalls() method of BouncingBallView so that both the screen and DB are cleared
    @Override
    public void wipeDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NAME, null, null);
        Log.v("DBClass", "wipeDatabase()=> " + deleted);
        db.close();
    }

    // Basic find by methods, they all get the first matching record
    @Override
    public DataModel findByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_1 + " = ?", new String[]{name}, null, null, null);
        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    @Override
    public DataModel findByX(float x) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_2 + " = ?", new String[]{String.valueOf(x)}, null, null, null);

        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    @Override
    public DataModel findByY(float y) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_3 + " = ?", new String[]{String.valueOf(y)}, null, null, null);

        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    @Override
    public DataModel findByDX(float dx) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_4 + " = ?", new String[]{String.valueOf(dx)}, null, null, null);

        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    @Override
    public DataModel findByDY(float dy) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_5 + " = ?", new String[]{String.valueOf(dy)}, null, null, null);

        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    @Override
    public DataModel findByColor(int color) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, _COL_6 + " = ?", new String[]{String.valueOf(color)}, null, null, null);

        DataModel dataModel = null;
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }
        cursor.close();
        db.close();
        return dataModel;
    }

    // Use this to test, finding a ball by all attributes
    public DataModel findByEverything(String name, float x, float y, float dx, float dy, int color) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                _COL_1 + " = ? AND " +
                _COL_2 + " = ? AND " +
                _COL_3 + " = ? AND " +
                _COL_4 + " = ? AND " +
                _COL_5 + " = ? AND " +
                _COL_6 + " = ?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{
                name,
                String.valueOf(x),
                String.valueOf(y),
                String.valueOf(dx),
                String.valueOf(dy),
                String.valueOf(color)
        });

        DataModel dataModel = null;

        // If a matching record is found, create the DataModel object
        if (cursor.moveToFirst()) {
            dataModel = new DataModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getInt(6)
            );
        }

        cursor.close();
        db.close();
        return dataModel;
    }


    // Now implemented!
    @Override
    public String getNameById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = null;
        String query = "SELECT " + _COL_1 + " FROM " + TABLE_NAME + " WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                name = cursor.getString(0);
            }
            cursor.close();
        }

        db.close();
        return name;
    }
}
