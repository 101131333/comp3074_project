package ca.gbc.comp3074.restaurantguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // TODO LIST
    // TODO 1: Not used functions are commented out. Kept if needed. To delete when project is done.

    // Initialize Database Name and Table Name
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String RESTO_TABLE_NAME = "restaurant_tbl";
    public static final String TAGS_TABLE_NAME = "tags_tbl";
    public static final String RESTO_COLUMN_ID = "id";
    public static final String RESTO_COLUMN_NAME = "name";
    public static final String RESTO_COLUMN_STREET = "street";
    public static final String RESTO_COLUMN_CITY = "city";
    public static final String RESTO_COLUMN_COUNTRY = "country";
    public static final String RESTO_COLUMN_POSTAL = "postal";
    public static final String RESTO_COLUMN_TAG = "tag";
    public static final String RESTO_COLUMN_PHONE = "phone";
    public static final String RESTO_COLUMN_DESC = "description";
    public static final String RESTO_COLUMN_RATE = "rate";
    //private HashMap hp;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        db.execSQL(
                    "CREATE TABLE restaurant_tbl " +
                        "(id INTEGER PRIMARY KEY, name TEXT, street TEXT, city TEXT, country TEXT, " +
                        "postal TEXT, tag TEXT, phone TEXT, rate TEXT, description TEXT)"
        );
        // separate table for tags
        db.execSQL(
                "CREATE TABLE tags_tbl (id INTEGER PRIMARY KEY, tags TEXT NOT NULL UNIQUE)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + RESTO_TABLE_NAME + " ");
        //db.execSQL("DROP TABLE IF EXISTS " + TAGS_TABLE_NAME + " ");
        onCreate(db);
    }

    // Clears/Reset Database
    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }

    // ADDS NEW RESTAURANT
    public boolean addResto(String name, String street, String city, String country, String postal, String tag,
                                 String phone, String rate, String description) {
        // Get WriteAble Database
        SQLiteDatabase db = this.getWritableDatabase();
        // create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("country", country);
        contentValues.put("postal", postal);
        contentValues.put("tag", tag);
        contentValues.put("phone", phone);
        contentValues.put("rate", rate);
        contentValues.put("description", description);
        db.insert(RESTO_TABLE_NAME, null, contentValues);
        return true;
    }

    // RETRIEVES RESTAURANT NAME BY ID
    public String getName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_NAME));
    }

    // RETRIEVES RESTAURANT STREET BY ID
    public String getStreet(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_STREET));
    }

    // RETRIEVES RESTAURANT CITY BY ID
    public String getCity(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_CITY));
    }

    // RETRIEVES RESTAURANT COUNTRY BY ID
    public String getCountry(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_COUNTRY));
    }

    // RETRIEVES RESTAURANT POSTAL BY ID
    public String getPostal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_POSTAL));
    }

    // RETRIEVES RESTAURANT TAG BY ID
    public String getTag(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_TAG));
    }

    // RETRIEVES RESTAURANT PHONE BY ID
    public String getPhone(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_PHONE));
    }

    // RETRIEVES RESTAURANT DESCRIPTION BY ID
    public String getDesc(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_DESC));
    }

    // RETRIEVES RESTAURANT RATE BY ID
    public String getRate(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESTO_TABLE_NAME + " WHERE " + RESTO_COLUMN_ID + " = '" + id + "'";

        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return res.getString(res.getColumnIndex(RESTO_COLUMN_RATE));
    }

    // TAGS
    // Preloaded tags
    public boolean insertTags(){
        String query = "INSERT OR IGNORE INTO tags_tbl (tags) VALUES " +
                "('Pizza'), ('Chinese'), ('Sushi'), ('Cafe'), " +
                "('Mexican Food'), ('Thai Food'), ('Seafood'), " +
                "('Indian Food'), ('Dessert'), ('Burgers'), " +
                "('Asian Food'), ('Italian Food'), ('Vegan'), " +
                "('Sandwiches'), ('Vegetarian'), ('Organic'), " +
                "('Italian Food'), ('Others')";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(query, null);
        res.moveToFirst();
        return true;
    }

    // retrieves all tags
    public ArrayList<String> getTags() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("SELECT * FROM " + TAGS_TABLE_NAME, null);
        Cursor res = db.rawQuery("SELECT DISTINCT tags FROM " + TAGS_TABLE_NAME +
                " ORDER BY tags ASC",null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("tags")));
            res.moveToNext();
        }
        return array_list;
    }



/*
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + RESTO_COLUMN_ID+ " FROM " + RESTO_TABLE_NAME +
                " WHERE " + RESTO_COLUMN_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
 */


    /*
    // COUNTS HOW MANY ARE STORED IN THE DATABASE
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RESTO_TABLE_NAME);
        return numRows;
    }
     */

    // EDITS RESTAURANT INFO
    public boolean updateResto(Integer id, String name, String street, String city, String country, String postal,
                               String tag, String phone, String rate, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("country", country);
        contentValues.put("postal", postal);
        contentValues.put("tag", tag);
        contentValues.put("phone", phone);
        contentValues.put("rate", rate);
        contentValues.put("description", description);
        // add values into database
        db.update(RESTO_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    // DELETES RESTAURANT
    public boolean deleteResto(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESTO_TABLE_NAME,"id = ? ",
                new String[]{Integer.toString(id)});
        return true;
    }

    // RETRIEVES ALL RESTAURANT NAMES IN DATABASE
    public ArrayList<String> getAllResto() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        // get readable database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + RESTO_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(RESTO_COLUMN_ID)) + " - " +
                    res.getString(res.getColumnIndex(RESTO_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
