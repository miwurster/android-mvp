package inf.msc.yawapp.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;

public class FavouritesStoreImpl extends SQLiteOpenHelper implements FavouritesStore {

    private static final String TAG = FavouritesStoreImpl.class.getSimpleName();

    // basic settings for database
    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;

    // table name
    private static final String FAV_TABLE = "favourites";

    // fields
    private static final String ID = "id";
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private static final String COUNTRY = "country";
    private static final String ZIP = "zip";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";

    // SQL Commands
    public static final String CREATE = "CREATE TABLE "
            + FAV_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LONGITUDE + " VARCHAR(255), "
            + LATITUDE + " VARCHAR(255), "
            + COUNTRY + " VARCHAR(255), "
            + ZIP + " VARCHAR(255), "
            + CITY + " VARCHAR(255), "
            + ADDRESS + " VARCHAR(255))";
    public static final String DROP = "DROP TABLE IF EXISTS " + FAV_TABLE;

    @Inject
    public FavouritesStoreImpl(MainApplication context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        db.execSQL(CREATE);
    }

    @Override
    public void add(Location loc) {
        SQLiteDatabase db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(LATITUDE, loc.getLatitude());
        values.put(LONGITUDE, loc.getLatitude());
        values.put(ZIP, loc.getZip());
        values.put(COUNTRY, loc.getCountry());
        values.put(CITY, loc.getCity());
        values.put(ADDRESS, loc.getAddress());

        long rowID = db.insert(FAV_TABLE, null, values);

        Log.d(TAG, "location added with rowID: " + rowID);
    }

    @Override
    public void update(Location loc) {
        SQLiteDatabase db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(LATITUDE, loc.getLatitude());
        values.put(LONGITUDE, loc.getLatitude());
        values.put(ZIP, loc.getZip());
        values.put(COUNTRY, loc.getCountry());
        values.put(CITY, loc.getCity());
        values.put(ADDRESS, loc.getAddress());

        long rowID = db.update(FAV_TABLE, values, ID + " = ? ", new String[]{Long.toString(loc.getId())});

        Log.d(TAG, "location updated with rowID: " + rowID);
    }

    @Override
    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        long rowID = db.delete(FAV_TABLE, ID + " = ? ", new String[]{Long.toString(id)});

        Log.d(TAG, "location deleted with rowID: " + rowID);
    }

    @Override
    public Location get(long id) {
        SQLiteDatabase db = getReadableDatabase();

        //SELECT * FROM FAV_TABLE WHERE ID = id GROUP BY CITY
        Cursor c = db.query(FAV_TABLE, null, ID + "= ?", new String[]{Long.toString(id)}, null, null, CITY);

        if (c.moveToFirst()) {
            return null;
        } //empty case

        //otherwise
        Location loc = new Location();
        do { //generally, there should only be ONE element
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(CITY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
        } while (c.moveToNext());

        c.close();

        return loc;
    }

    @Override
    public List<Location> getAll() {
        SQLiteDatabase db = getReadableDatabase();

        //SELECT * FROM FAV_TABLE GROUP BY CITY
        Cursor c = db.query(FAV_TABLE, null, null, null, null, null, CITY);

        if (!c.moveToFirst()) {
            return new ArrayList<Location>();
        } //empty case

        //otherwise
        List<Location> locs = new ArrayList<Location>();
        do { //generally, there should only be ONE element
            Location loc = new Location();
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(CITY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));

            locs.add(loc);
        } while (c.moveToNext());

        c.close();
        return locs;
    }

    @Override
    public List<Location> search(String query) {
        SQLiteDatabase db = getReadableDatabase();

        //SELECT * FROM FAV_TABLE WHERE [wh_String] GROUP BY CITY
        String wh_String = CITY + " = ? OR " +
                ZIP + " = ? OR " +
                COUNTRY + " = ? OR " +
                ADDRESS + " = ? ";
        String[] queries = new String[]{query, query, query, query}; //for each ? in wh_String

        Cursor c = db.query(FAV_TABLE, null, wh_String, queries, null, null, CITY);

        if (c.moveToFirst()) {
            return new ArrayList<Location>();
        } //empty case

        //otherwise
        List<Location> locs = new ArrayList<Location>();
        do { //generally, there should only be ONE element
            Location loc = new Location();
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(CITY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));

            locs.add(loc);
        } while (c.moveToNext());
        c.close();

        return locs;
    }
}
