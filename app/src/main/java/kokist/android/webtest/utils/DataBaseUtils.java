package kokist.android.webtest.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2015/5/24.
 */
public class DataBaseUtils {

    private  Context context;
    public  DataBaseUtils(Context context){
        this.context=context;
    }

    private  SQLiteDatabase db;

    public long InsertValue(ContentValues cv,String table){
        DbHelper dbHelper=new DbHelper(context,"user",1);
        db= dbHelper.getWritableDatabase();
        return db.insert(table,null,cv);
    }
    public   int Update(ContentValues cv,String table,String wherecase,String[] values){
        DbHelper dbHelper=new DbHelper(context,"user",1);
        db=dbHelper.getWritableDatabase();
        return db.update(table, cv, wherecase, values);
    }
    public  Cursor Query(String wherecase,String[] values){
        DbHelper dbHelper=new DbHelper(context,"user",1);
        db=dbHelper.getReadableDatabase();
        return db.rawQuery(wherecase, values);
    }
}
