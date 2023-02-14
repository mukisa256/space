package com.example.saftyapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATEBASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactdata";

    private static  final String TABLE_NAME = "contact";

    //columns
    private  static  final  String KEY_ID = "id";
    private  static  final  String NAME = "Name";
    private  static  final  String PHONENO = " phoneNo";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATEBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table for the first time
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + PHONENO + " TEXT" + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // method to add the contact
    public void addcontact(ContactsModel contact){
          SQLiteDatabase db = this.getWritableDatabase();
         ContentValues c = new ContentValues();
         c.put(NAME,contact.getName());
         c.put(PHONENO,contact.getPhoneNo());
         db.insert(TABLE_NAME,null,c);
         db.close();

    }
    // method to retrieve all the contacts in list
    public List<ContactsModel> getAllContacts(){
        List<ContactsModel> list = new ArrayList<>();
        String query = " SELECT*FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        if (c.moveToFirst()){
            do {
                list.add(new ContactsModel(c.getInt(0),c.getString(1),c.getString(2)));

            } while (c.moveToNext());
        }
         return list;
    }
      //get the count of data,this will allow user
    //to not add more than five contacts in database
    public int count(){
        int count =0;
        String query = " SELECT COUNT(*) FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);
        if (c.getCount()>0){
            c.moveToFirst();
            count = c.getInt(0);

        }
        c.close();
        return count;
    }
    //delect single country
    public void deleteContact(ContactsModel contact){
        SQLiteDatabase db = this.getWritableDatabase();
        int i= db.delete(TABLE_NAME,KEY_ID + " =? ",new String[]{ String.valueOf(contact.getId())});

        db.close();
    }
}

