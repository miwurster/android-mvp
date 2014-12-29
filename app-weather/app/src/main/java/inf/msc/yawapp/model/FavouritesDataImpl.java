package inf.msc.yawapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;

/**
 * Created by Sebastian on 29.12.2014.
 */
public class FavouritesDataImpl extends SQLiteOpenHelper implements FavouritesData {

    private static final String TAG = FavouritesDataImpl.class.getSimpleName();
    private final boolean DEBUG = true;

    //basic settings for database
    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 3;

    //table name
    private static final String FAV_TABLE = "favourites";

    //fields
    private static final String ID = "id";
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private static final String COUNTRY = "country";
    private static final String ZIP = "zip";
    private static final String CITY = "city";
    private static final String ADDRESS = "address";

    //SQL Commands
    public static final String CREATE = "CREATE TABLE "
            +FAV_TABLE+" ("
            +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +LONGITUDE+" VARCHAR(255), "
            +LATITUDE+" VARCHAR(255), "
            +COUNTRY+" VARCHAR(255), "
            +ZIP+" VARCHAR(255), "
            +CITY+" VARCHAR(255), "
            +ADDRESS+" VARCHAR(255))";
            //+"PRIMARY KEY ("+ID+"))";
    public static final String DROP = "DROP TABLE IF EXISTS "+ FAV_TABLE;


    private SQLiteDatabase db;

    /**
     * Constructor
     * @param context
     */
    @Inject
    public FavouritesDataImpl (MainApplication context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteDatabase getSQLiteDB(){
        if(this.db == null){
            this.db = getWritableDatabase();
        }
        return this.db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE);



        if(DEBUG){
            Location l0 = new Location();
            l0.setCity("Stuttgart");
            l0.setZip("73445");
            l0.setCountry("DE");
            l0.setAddress("Königsstraße 10");
            this.add(l0);

            Location l1 = new Location();
            l1.setCity("München");
            l1.setZip("80001");
            l1.setCountry("DE");
            l1.setAddress("");
            this.add(l1);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;
        db.execSQL(DROP);
        onCreate(db);
    }

    @Override
    public void add(Location loc) {

        //db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(LATITUDE,loc.getLatitude());
        values.put(LONGITUDE,loc.getLatitude());
        values.put(ZIP,loc.getZip());
        values.put(COUNTRY,loc.getCountry());
        values.put(CITY,loc.getCity());
        values.put(ADDRESS,loc.getAddress());

        long rowID = getSQLiteDB().insert(FAV_TABLE,null,values);

        Log.d(TAG,"location added with rowID: "+rowID);

    }

    @Override
    public void update(Location loc) {

        //SQLiteDatabase db = getWritableDatabase();

        //Entry:
        ContentValues values = new ContentValues();
        values.put(LATITUDE,loc.getLatitude());
        values.put(LONGITUDE,loc.getLatitude());
        values.put(ZIP,loc.getZip());
        values.put(COUNTRY,loc.getCountry());
        values.put(CITY,loc.getCity());
        values.put(ADDRESS,loc.getAddress());

        long rowID = getSQLiteDB().update(FAV_TABLE,values,ID+ " = ? ",new String[]{Long.toString(loc.getId())});

        Log.d(TAG,"location updated with rowID: "+rowID);



    }

    @Override
    public void delete(long id) {
        //SQLiteDatabase db = getWritableDatabase();
        long rowID = getSQLiteDB().delete(FAV_TABLE, ID + " = ? ", new String[]{Long.toString(id)});

        Log.d(TAG,"location deleted with rowID: "+rowID);
    }

    @Override
    public Location get(long id) {

        //SQLiteDatabase db = getWritableDatabase();


        //SELECT * FROM FAV_TABLE WHERE ID = id GROUP BY CITY
        Cursor c = getSQLiteDB().query(FAV_TABLE,null,ID + "= ?", new String[]{Long.toString(id)},null,null,CITY);

        if(!c.moveToFirst()){ return null;} //empty case

        //otherwise
        Location loc = new Location();
        do{ //generally, there should only be ONE element
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(COUNTRY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
        }while(c.moveToNext());

        c.close();

        return loc;
    }

    @Override
    public List<Location> getAll() {
        //SQLiteDatabase db = getWritableDatabase();
        Log.d(TAG,"getAll SQL");

        //SELECT * FROM FAV_TABLE GROUP BY CITY
        Cursor c = getSQLiteDB().query(FAV_TABLE,null,null, null,null,null,CITY);

        if(!c.moveToFirst()){

            Log.d(TAG,"Cursor is empty");
            return new ArrayList<Location>();

        } //empty case

        //otherwise
       List<Location> locs = new ArrayList<Location>();
        do{ //generally, there should only be ONE element
            Location loc = new Location();
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(COUNTRY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));
            Log.d(TAG,"Location loaded with ID "+loc.getId());

            locs.add(loc);
        }while(c.moveToNext());

        c.close();
        return locs;
    }

    @Override
    public List<Location> search(String query) {
        //SQLiteDatabase db = getWritableDatabase();


        //SELECT * FROM FAV_TABLE WHERE [wh_String] GROUP BY CITY
        String wh_String =  CITY +" LIKE ? OR "+
                            ZIP +" LIKE ? OR "+
                            COUNTRY +" LIKE ? OR "+
                            ADDRESS +" LIKE ? ";
        String[] queries = new String[]{"%"+query+"%",query+"%","%"+query+"%","%"+query+"%"}; //for each ? in wh_String

        Cursor c = getSQLiteDB().query(FAV_TABLE,null,wh_String, queries,null,null,CITY);



        if(!c.moveToFirst()){ return new ArrayList<Location>();} //empty case

        //otherwise
        List<Location> locs = new ArrayList<Location>();
        do{ //generally, there should only be ONE element
            Location loc = new Location();
            loc.setId(c.getLong(c.getColumnIndex(ID)));
            loc.setLongitude(c.getString(c.getColumnIndex(LONGITUDE)));
            loc.setLatitude(c.getString(c.getColumnIndex(LATITUDE)));
            loc.setCountry(c.getString(c.getColumnIndex(COUNTRY)));
            loc.setZip(c.getString(c.getColumnIndex(ZIP)));
            loc.setCity(c.getString(c.getColumnIndex(CITY)));
            loc.setAddress(c.getString(c.getColumnIndex(ADDRESS)));

            locs.add(loc);
        }while(c.moveToNext());
        c.close();

        return locs;
    }
}
