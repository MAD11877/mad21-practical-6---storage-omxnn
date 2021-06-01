package sg.edu.np.prac3githhub;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    static ArrayList<User> userDBList = new ArrayList<>();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String USERS = "Users";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_FOLLOWED = "Followed";

    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory,
                       int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + USERS + "(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " TEXT," + COLUMN_FOLLOWED + " TEXT" +")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);

    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDesc());
        values.put(COLUMN_ID, user.getId());
        if (user.followed == true){
            values.put(COLUMN_FOLLOWED, "1");
        } else{
            values.put(COLUMN_FOLLOWED, "0");
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        User queryData = new User();

        String query = "SELECT * FROM " + USERS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                userDBList.add(new User(cursor.getString(0), cursor.getInt(2),
                        cursor.getString(1), cursor.getInt(3) == 1));
            } while (cursor.moveToNext());
        }

        db.close();
        return (userDBList);

    }

    public void updateUser(User userUpdate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(userUpdate.followed == false) {
            cv.put(COLUMN_FOLLOWED, 0);
        } else {
            cv.put(COLUMN_FOLLOWED, 1);
        }
        db.update(USERS, cv, "Id=?", new String[]{String.valueOf(userUpdate.getId())});
        db.close();
    }

}