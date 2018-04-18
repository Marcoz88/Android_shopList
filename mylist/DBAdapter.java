import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    public static final String TABLE_NAME = "store";
    public static final String COL_ITEM_ID = "_id";
    public static final String COL_ITEM_NAME = "name";
    public static final String COL_ITEM_AMOUNT = "amount";

    public DBAdapter(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public Cursor getItems() {
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_ITEM_ID, COL_ITEM_NAME, COL_ITEM_AMOUNT}, null, null, null, null, COL_ITEM_NAME);
        return cursor;
    }

    public void storeItem(String name, int amount) {
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME, name);
        values.put(COL_ITEM_AMOUNT, amount);
        db.insert(TABLE_NAME, null, values);
    }

    public void removeItem(String item) {
        db.delete(TABLE_NAME, COL_ITEM_NAME + "= '" + item + "';", null);
    }

    public boolean contains(String item) {
        return db.query(TABLE_NAME, new String[]{COL_ITEM_ID, COL_ITEM_NAME}, COL_ITEM_NAME + "= '" + item + "';", null, null, null, COL_ITEM_NAME).getCount() != 0;
    }

    public void increment(String item) {
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME, item);
        Cursor amount = db.query(TABLE_NAME, new String[]{COL_ITEM_ID, COL_ITEM_AMOUNT}, COL_ITEM_NAME + "= '" + item + "';", null, null, null, COL_ITEM_NAME);
        amount.moveToFirst();
        values.put(COL_ITEM_AMOUNT, amount.getInt(amount.getColumnIndex(COL_ITEM_AMOUNT)) + 1);
        this.removeItem(item);
        db.insert(TABLE_NAME, null, values);
    }

    public void decrement(String item) {
        ContentValues values = new ContentValues();
        values.put(COL_ITEM_NAME, item);
        Cursor c = db.query(TABLE_NAME, new String[]{COL_ITEM_ID, COL_ITEM_AMOUNT}, COL_ITEM_NAME + "= '" + item + "';", null, null, null, COL_ITEM_NAME);
        c.moveToFirst();
        values.put(COL_ITEM_AMOUNT, c.getInt(c.getColumnIndex(COL_ITEM_AMOUNT)) - 1);
        if (c.getInt(c.getColumnIndex(COL_ITEM_AMOUNT)) == 0) {
            this.removeItem(item);
        } else {
            this.removeItem(item);
            db.insert(TABLE_NAME, null, values);
        }
    }

    public long getTotal() {
        String[] columns = new String[]{COL_ITEM_ID, COL_ITEM_NAME, COL_ITEM_AMOUNT};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);
        long total = DatabaseUtils.longForQuery(db, "select sum(" + COL_ITEM_AMOUNT + ") from " + TABLE_NAME, null);
        return total;
    }


    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}
