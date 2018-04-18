import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ItemAppDB";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE_STORE = "CREATE TABLE "+ DBAdapter.TABLE_NAME + " ("+ DBAdapter.COL_ITEM_ID +
            " integer primary key autoincrement, " +DBAdapter.COL_ITEM_NAME + " text, "+DBAdapter.COL_ITEM_AMOUNT+" text)";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table");
        onCreate(db);
    }
}
