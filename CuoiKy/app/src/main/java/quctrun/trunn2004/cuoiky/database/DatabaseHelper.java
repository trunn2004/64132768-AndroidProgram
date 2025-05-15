package quctrun.trunn2004.cuoiky.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "chitien.db";

    public static final String TABLE_USER = "users";
    public static final String TABLE_TRANSACTION = "transactions";
    public static final String TABLE_PIN = "pin";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng tài khoản
        db.execSQL("CREATE TABLE " + TABLE_USER + " (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT)");

        // Tạo bảng thu chi
        db.execSQL("CREATE TABLE " + TABLE_TRANSACTION + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "type TEXT," + // income or expense
                "category TEXT," +
                "amount REAL," +
                "note TEXT," +
                "date TEXT)");

        // Bảng lưu mã PIN
        db.execSQL("CREATE TABLE " + TABLE_PIN + " (user_id INTEGER PRIMARY KEY, pin TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIN);
        onCreate(db);
    }

    // Đăng ký tài khoản
    public boolean registerUser(String email, String password) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        long result = getWritableDatabase().insert(TABLE_USER, null, values);
        return result != -1;
    }

    // Đăng nhập tài khoản
    public Cursor loginUser(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE email = ? AND password = ?", new String[]{email, password});
    }

    // Thêm khoản thu chi
    public boolean insertTransaction(int userId, String type, String category, double amount, String note, String date) {
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("type", type);
        values.put("category", category);
        values.put("amount", amount);
        values.put("note", note);
        values.put("date", date);
        long result = getWritableDatabase().insert(TABLE_TRANSACTION, null, values);
        return result != -1;
    }

    // Lấy tất cả khoản thu chi theo người dùng
    public Cursor getTransactionsByUser(int userId) {
        return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_TRANSACTION + " WHERE user_id = ?", new String[]{String.valueOf(userId)});
    }

    // Lưu mã PIN
    public void savePin(int userId, String pin) {
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("pin", pin);
        getWritableDatabase().insertWithOnConflict(TABLE_PIN, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    // Lấy mã PIN
    public String getPin(int userId) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT pin FROM " + TABLE_PIN + " WHERE user_id = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return null;
    }

    // Tổng thu chi
    public Cursor getTotalByType(int userId, String type) {
        return getReadableDatabase().rawQuery(
                "SELECT SUM(amount) FROM " + TABLE_TRANSACTION + " WHERE user_id = ? AND type = ?",
                new String[]{String.valueOf(userId), type});
    }

    // Tìm kiếm theo từ khóa
    public Cursor searchTransactions(int userId, String keyword) {
        return getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_TRANSACTION + " WHERE user_id = ? AND (note LIKE ? OR category LIKE ?)",
                new String[]{String.valueOf(userId), "%" + keyword + "%", "%" + keyword + "%"});
    }
}
