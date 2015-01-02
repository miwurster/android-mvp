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
import inf.msc.yawapp.common.GenericObservable;

public class FavouritesStoreImpl extends SQLiteOpenHelper implements FavouritesStore {

    @Inject
    GenericObservable<FavouritesStoreOperation> favouritesStoreObservable;

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
    private static final String CITY = "city";

    // SQL Commands
    public static final String CREATE = "CREATE TABLE "
            + FAV_TABLE + " ("
            + ID + " INTEGER PRIMARY KEY, "
            + LONGITUDE + " REAL, "
            + LATITUDE + " REAL, "
            + COUNTRY + " VARCHAR(255), "
            + CITY + " VARCHAR(255))";
    public static final String DROP = "DROP TABLE IF EXISTS " + FAV_TABLE;

    private class StoreOperation implements FavouritesStoreOperation {
        private Operation op;
        private Location location;

        public StoreOperation(Operation op, Location location) {
            this.op = op;
            this.location = location;
        }

        @Override
        public Operation getOperation() {
            return op;
        }

        @Override
        public Location getLocation() {
            return location;
        }
    }

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
    public void add(final Location location) {
        SQLiteDatabase db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(ID, location.getId());
        values.put(LATITUDE, location.getLatitude());
        values.put(LONGITUDE, location.getLongitude());
        values.put(COUNTRY, location.getCountry());
        values.put(CITY, location.getCity());

        long rowID = db.insert(FAV_TABLE, null, values);
        if (rowID != -1) {
            Log.d(TAG, "location added with rowID: " + rowID);
            favouritesStoreObservable.notifyAll(new StoreOperation(FavouritesStoreOperation.Operation.ADDED, location));
        }
    }

    @Override
    public void update(Location location) {
        SQLiteDatabase db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(LATITUDE, location.getLatitude());
        values.put(LONGITUDE, location.getLongitude());
        values.put(COUNTRY, location.getCountry());
        values.put(CITY, location.getCity());

        long rowID = db.update(FAV_TABLE, values, ID + " = ? ", new String[]{Long.toString(location.getId())});

        Log.d(TAG, "location updated with rowID: " + rowID);
        favouritesStoreObservable.notifyAll(new StoreOperation(FavouritesStoreOperation.Operation.UPDATED, location));
    }

    @Override
    public void delete(Location location) {
        SQLiteDatabase db = getWritableDatabase();
        long rowID = db.delete(FAV_TABLE, ID + " = ? ", new String[]{Long.toString(location.getId())});

        Log.d(TAG, "location deleted with rowID: " + rowID);
        favouritesStoreObservable.notifyAll(new StoreOperation(FavouritesStoreOperation.Operation.DELETED, location));
    }

    @Override
    public Location getById(long id) {
        SQLiteDatabase db = getReadableDatabase();

        //SELECT * FROM FAV_TABLE WHERE ID = id GROUP BY CITY
        Cursor c = db.query(FAV_TABLE, null, ID + "= ?", new String[]{Long.toString(id)}, null, null, CITY);

        if (!c.moveToFirst()) {
            return null;
        } //empty case

        //otherwise
        MutableLocation loc = new FavouritesStoreEntry();
        loc.setId(c.getLong(c.getColumnIndex(ID)));
        loc.setLongitude(c.getFloat(c.getColumnIndex(LONGITUDE)));
        loc.setLatitude(c.getFloat(c.getColumnIndex(LATITUDE)));
        loc.setCountry(c.getString(c.getColumnIndex(COUNTRY)));
        loc.setCity(c.getString(c.getColumnIndex(CITY)));

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

        List<Location> result = new ArrayList<Location>();
        do {
            MutableLocation loc = new FavouritesStoreEntry();
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getFloat(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getFloat(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(COUNTRY)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));

            result.add(loc);
        } while (c.moveToNext());

        c.close();
        return result;
    }

}
