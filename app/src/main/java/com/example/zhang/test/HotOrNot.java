package com.example.zhang.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot  {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_HOTNESS = "person_hotness";

    private static final String DATABASE_NAME = "HotOrNot.db";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DBHelper myHelper;
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    public HotOrNot(Context context) {
        myContext = context;
    }

    public long createEntry(String name, String hotness) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_HOTNESS, hotness);
        return myDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData() {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        String result = "";

        Cursor c = myDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
        int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iHotness = c.getColumnIndex(KEY_HOTNESS);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result += c.getString(iRow) + "  " + c.getString(iName) + "  " + c.getString(iHotness) + "\n";
        }

        return result;
    }

    public String getName(long l) {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,null,null,null);
        if(c != null){
            c.moveToFirst();
            return c.getString(1);
        }
        return null;
    }

    public String getHotness(long l) {
        String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null,null,null,null);
        if(c != null){
            c.moveToFirst();
            return c.getString(2);
        }
        return null;
    }

    public void updateEntry(long lRow, String mName, String mHotness) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_HOTNESS, mHotness);
        myDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + '=' + lRow, null);
    }

    private static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "
                    + DATABASE_TABLE + " ("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_NAME + " TEXT NOT NULL, "
                    + KEY_HOTNESS + "TEXT NOT NULL);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public HotOrNot open() throws SQLException{
        myHelper = new DBHelper(myContext);
        myDatabase = myHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        myHelper.close();
    }
}
