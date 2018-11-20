package com.example.juyelrana.contactinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JuyelRana on 11/15/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Config.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addContact(Contact contact) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        //Store student data to database
        cv.put(Config.NAME, contact.getName());
        cv.put(Config.PHONE, contact.getPhone());
        cv.put(Config.EMAIL, contact.getEmail());

        long inserted = database.insert(Config.TABLE_NAME, "", cv);
        database.close();

        return inserted;
    }

    public List<Contact> getContactList() {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(Config.SELECT_SQL, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}
