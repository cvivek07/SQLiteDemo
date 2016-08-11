package google.csc.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vivek on 11-Aug-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "USERS_DATABASE";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CREATE = "create table USERS (_id integer primary key AUTOINCREMENT, " +
            " NAME text not null, EMAIL text not null, PHONE text not null, PASSWORD text not null);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "USERS");
        onCreate(sqLiteDatabase);
    }

    public boolean saveData(String name, String email, String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("EMAIL", email);
        values.put("PHONE", phone);
        values.put("PASSWORD", password);
        long result = db.insert("USERS", null, values);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public String checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query("USERS", new String[]{"EMAIL", "PASSWORD"}, "EMAIL= ? AND PASSWORD=?",
                new String[]{email, password}, null, null, null);


        while (res.moveToNext()) {
           /* String email1 = res.getString(res.getColumnIndex("EMAIL"));
            String pass1 = res.getString(res.getColumnIndex("PASSWORD"));*/
            //Log.v("TAG" , email1 + " " + pass1);
            return "data";
        }

        res.close();
        db.close();

        return "no data";

    }
}
