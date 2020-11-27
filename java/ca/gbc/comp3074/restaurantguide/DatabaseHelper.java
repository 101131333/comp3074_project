package ca.gbc.comp3074.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Initialize Database Name and Table Name
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String RESTO_TABLE_NAME = "restaurant_tbl";
    public static final String RESTO_COLUMN_ID = "id";
    public static final String RESTO_COLUMN_NAME = "name";
    public static final String RESTO_COLUMN_EMAIL = "email";
    public static final String RESTO_COLUMN_STREET = "street";
    public static final String RESTO_COLUMN_CITY = "place";
    public static final String RESTO_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        db.execSQL(
                    "CREATE TABLE restaurant_tbl " +
                        "(id INTEGER PRIMARY KEY, name TEXT, street TEXT, city TEXT, " +
                        "postal TEXT, phone TEXT, rate TEXT, description TEXT)"
        );
        // TODO: add table for tags
        // separate table for tags
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS RESTO_TABLE_NAME");
        onCreate(db);
    }


    // ADDS NEW RESTAURANT
    public boolean addResto(String name, String street, String city, String postal,
                                 String phone, float rate, String description) {
        // Get WriteAble Database
        SQLiteDatabase db = this.getWritableDatabase();
        // create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("postal", postal);
        contentValues.put("phone", phone);
        contentValues.put("rate", rate);
        contentValues.put("description", description);
        db.insert(RESTO_TABLE_NAME, null, contentValues);
        return true;
    }


    // RETRIEVES RESTAURANT INFO BY ID
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM RESTO_TABLE_NAME WHERE ID =" + id + "", null);
        return res;
    }


    // COUNTS HOW MANY ARE STORED IN THE DATABASE
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RESTO_TABLE_NAME);
        return numRows;
    }


    // EDITS RESTAURANT INFO
    public boolean updateResto(Integer id, String name, String street, String city, String postal,
                                 String phone, float rate, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("postal", postal);
        contentValues.put("phone", phone);
        contentValues.put("rate", rate);
        contentValues.put("description", description);
        // add values into database
        db.update(RESTO_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }


    // DELETES RESTAURANT
    public Integer deleteResto(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RESTO_TABLE_NAME,"id = ? ",
                new String[]{Integer.toString(id)});
    }


    // RETRIEVES ALL RESTAURANT INFO IN DATABASE
    public ArrayList<String> getAllResto() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + RESTO_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            // TODO: add all info that needs to be retrieved
            array_list.add(res.getString(res.getColumnIndex(RESTO_COLUMN_NAME)));
            array_list.add(res.getString(res.getColumnIndex(RESTO_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}
