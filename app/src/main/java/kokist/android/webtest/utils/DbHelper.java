package kokist.android.webtest.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/5/23.
 */
public class DbHelper extends SQLiteOpenHelper {
    private final static String CREATE_TBL = "CREATE TABLE user(_id integer primary key autoincrement, username text not null, password text not null, phonenumber text, desc text,UNIQUE(username));";
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DbHelper(Context context,String name,int version){
        this(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(CREATE_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
