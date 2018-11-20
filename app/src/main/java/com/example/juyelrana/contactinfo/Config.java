package com.example.juyelrana.contactinfo;

/**
 * Created by JuyelRana on 11/15/2018.
 */

public class Config {
    public static final String DB_NAME = "mydb";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "contact";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PHONE + " TEXT, " +
            EMAIL + " TEXT)";

    public static final String SELECT_SQL = "select * from " + TABLE_NAME + " order by " + ID + " desc";
}
