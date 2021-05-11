package net.mbmedia.coronaampel.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.mbmedia.coronaampel.to.RkiTO;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "net.mbmedia.corona";
    public static final Integer DATABASE_VERSION = 1;

    public final String RKI_TABLE_NAME = "RKI";
    public final String FAV_TABLE_NAME = "FAV";
    private final String FAV_OBJECTID = "objectid";

    private final String OBJECTID = "objectid";
    private final String GEN = "gen";
    private final String BEZ = "bez";
    private final String DEATH_RATE = "death_rate";
    private final String CASES = "cases";
    private final String DEATHS = "deaths";
    private final String CASES_PER_100K = "cases_100k";
    private final String CASES_PER_POPULATION = "cases_pop";
    private final String BL = "bl";
    private final String CASES7_PER_100K = "cases7_100k";
    private final String RECOVERED = "recovered";
    private final String LAST_UPDATE_ID = "last_u_id";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRKITable = "CREATE TABLE " + RKI_TABLE_NAME + "(" +
                OBJECTID + " INTEGER, " +
                GEN + " TEXT," +
                BEZ + " TEXT," +
                DEATH_RATE + " TEXT," +
                CASES + " INTEGER," +
                DEATHS + " INTEGER," +
                CASES_PER_100K + " TEXT," +
                CASES_PER_POPULATION + " TEXT," +
                BL + " TEXT," +
                CASES7_PER_100K + " TEXT," +
                RECOVERED + " INTEGER," +
                LAST_UPDATE_ID + " INTEGER" + ")";

        db.execSQL(createRKITable);

        String createFavouritesTable = "CREATE TABLE " + FAV_TABLE_NAME + "(" +
                FAV_OBJECTID + " INTEGER" + ")";
        db.execSQL(createFavouritesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public ArrayList<RkiTO> getAll(){
        ArrayList<RkiTO> TOs = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + RKI_TABLE_NAME + " ORDER BY " + GEN + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                RkiTO to = new RkiTO();
                to.setObjectid(c.getInt(0));
                to.setGen(c.getString(1));
                to.setBez(c.getString(2));
                to.setDeath_rate(c.getString(3));
                to.setCases(c.getInt(4));
                to.setDeaths(c.getInt(5));
                to.setCases_per_100k(c.getString(6));
                to.setCases_per_population(c.getString(7));
                to.setBl(c.getString(8));
                to.setCases7_per_100k(c.getString(9));
                to.setRecovered(c.getInt(10));
                to.setLast_update_id(c.getInt(11));

                TOs.add(to);

            } while (c.moveToNext());
        }

        return  TOs;
    }

    public int getLastUpdateID(){
        String sql = "SELECT " + LAST_UPDATE_ID + " FROM "+ RKI_TABLE_NAME + " LIMIT 1;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int lastupdate = 0;
        if (cursor.moveToFirst()) {
            do {
                lastupdate = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        return lastupdate;
    }

    public void update(ArrayList<RkiTO> list) {
        deleteAll();
        insertAll(list);
    }

    private void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RKI_TABLE_NAME, "1=1", null);
        db.close();
    }

    private void insertAll(ArrayList<RkiTO> TOs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(RkiTO to : TOs){
            values.put(OBJECTID, to.getObjectid());
            values.put(GEN, to.getGen());
            values.put(BEZ, to.getBez());
            values.put(DEATH_RATE, to.getDeath_rate());
            values.put(CASES, to.getCases());
            values.put(DEATHS, to.getDeaths());
            values.put(CASES_PER_100K, to.getCases_per_100k());
            values.put(CASES_PER_POPULATION, to.getCases_per_population());
            values.put(BL, to.getBl());
            values.put(CASES7_PER_100K, to.getCases7_per_100k());
            values.put(RECOVERED, to.getRecovered());
            values.put(LAST_UPDATE_ID, to.getLast_update_id());

            db.insert(RKI_TABLE_NAME, null, values);
        }

        db.close();
    }

    public ArrayList<Integer> getFavourites(){
        ArrayList<Integer> TOs = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + FAV_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                TOs.add(c.getInt(0));

            } while (c.moveToNext());
        }

        return  TOs;
    }

    public void addFavourite(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FAV_OBJECTID, i);
        db.insert(FAV_TABLE_NAME, null, values);
        db.close();
    }

    public void delFavourite(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FAV_TABLE_NAME, FAV_OBJECTID + " = ?", new String[] { String.valueOf(i) });
        db.close();
    }
}
