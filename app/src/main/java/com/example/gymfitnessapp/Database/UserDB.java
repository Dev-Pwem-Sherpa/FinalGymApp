package com.example.gymfitnessapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.gymfitnessapp.Model.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class UserDB extends SQLiteAssetHelper {
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;

    public UserDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        // Ensure the Users table exists when the database is opened
        ensureUsersTableExists();
    }

    // Ensures the Users table exists
    private void ensureUsersTableExists() {
        SQLiteDatabase db = getWritableDatabase();

        try {
            // Check if the "Users" table exists
            Cursor cursor = db.rawQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name='Users';",
                    null
            );

            boolean tableExists = cursor.moveToFirst();
            cursor.close();

            if (!tableExists) {
                // If the table doesn't exist, create it
                db.execSQL("CREATE TABLE IF NOT EXISTS Users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "email TEXT NOT NULL UNIQUE," +
                        "bmi DOUBLE," +
                        "password TEXT NOT NULL)");
                Log.d("UserDB", "Users table created successfully.");
            } else {
                Log.d("UserDB", "Users table already exists.");
            }
        } catch (Exception e) {
            Log.e("UserDB", "Error checking/creating Users table", e);
        }
    }

    public User getUser(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM Users WHERE email = ?";
        Log.d("Email", email);
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email});

        Log.d("Users", cursor.getColumnName(0));

        if (cursor.moveToFirst()) {
            // Build a User object from the result
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            cursor.close();
            Log.d("User", user.getName());
            return user;
        } else {
            cursor.close();
            return null;
        }
    }

    public void saveUser(@NonNull User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = String.format("INSERT INTO Users(name, email, password) VALUES('%s', '%s', '%s')",
                user.getName(), user.getEmail(), user.getPassword());
        sqLiteDatabase.execSQL(query);
    }

    public boolean updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues args = new ContentValues();
        args.put("name", user.getName());
        Log.d("User Name", user.getName());
        String whereClause = String.format("email = '%s'", user.getEmail());
        sqLiteDatabase.update("users", args, whereClause, null);
        return  true;
    }

    public void deleteTable(String table_name) {
        if (!table_name.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Invalid table name");
        }

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            String query = String.format("DROP TABLE IF EXISTS %s;", table_name);
            sqLiteDatabase.execSQL(query);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace(); // Log error
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
}