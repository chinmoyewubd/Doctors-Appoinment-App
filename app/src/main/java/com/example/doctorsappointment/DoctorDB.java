package com.example.doctorsappointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DoctorDB extends SQLiteOpenHelper {
    public DoctorDB(Context context) {

        super(context, "DoctorDB.db", null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB@OnCreate");
        String sql = "CREATE TABLE doctor  ("
                + "ID TEXT,"
                + "name TEXT,"
                + "speciality TEXT,"
                + "consultationstart TEXT,"
                + "consultationend TEXT,"
                + "contact TEXT"
                + ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Write code to modify database schema here");
        // db.execSQL("ALTER table my_table  ......");
    }
    public void insertDoctor(String id,String name,String speciality,String consultationstart,String consultationend,String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put("ID", id);
        cols.put("name", name);
        cols.put("speciality", speciality);
        cols.put("consultationstart", consultationstart);
        cols.put("consultationend", consultationend);
        cols.put("contact",contact );
        db.insert("doctor", null ,  cols);
        db.close();
    }
    public void updateDoctor(String studentID, String course, long date, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put("status", status);
        db.update("doctor", cols, "ID=?,course=?,date=?", new String[] {studentID, course, String.valueOf(date)} );
        db.close();
    }
    public void deleteAttendance(String studentID, String course, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("doctor", "ID=?,course=?,date=?", new String[] {studentID, course, String.valueOf(date)} );
        db.close();
    }
    public Cursor selectDoctor(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = null;
        try {
            c = db.rawQuery(query, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }

    public void clearDoctorsTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("doctor", null, null);
        db.close();
    }
}
