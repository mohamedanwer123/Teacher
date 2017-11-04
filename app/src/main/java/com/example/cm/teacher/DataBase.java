package com.example.cm.teacher;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by cm on 12/08/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static  final String  create_table = "create table student_teacher0 ( id INTEGER PRIMARY KEY AUTOINCREMENT , name VARCHAR(35) , Presence INTEGER , Absence INTEGER , Pay INTEGER  )";

    public static  final String  create_table_marks = "create table marks0 ( id INTEGER , mark VARCHAR(10) )";

    public DataBase(Context context) {
        super(context, "Teacher_database0", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
        sqLiteDatabase.execSQL(create_table_marks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE If EXIST student_teacher0");
        sqLiteDatabase.execSQL("DROP TABLE If EXIST marks0");
        onCreate(sqLiteDatabase);
    }

    /************************* add student name (alert add) *************************/
    public  boolean insert_student_name(String name)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
       long res = sqLiteDatabase.insert("student_teacher0",null,contentValues);
        if(res == -1)
        {
            return false;
        }else
        {
            return  true;
        }

    }

    /************************* read student name & id *************************/

    public ArrayList read_name_and_id()
    {
       ArrayList arrayList = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from student_teacher0",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String id = cursor.getString(0);
            String name = cursor.getString(1);

            arrayList.add(id+"/"+name);
            cursor.moveToNext();
        }

        return arrayList;
    }

    /************************* delete student *************************/

    public boolean delete_student(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
       long b = sqLiteDatabase.delete("student_teacher0", "id=?", new String[]{id});

        if(b==1)
        {
            return  true;
        }else
        {
            return  false;
        }
    }

    /************************* write the Presence of student *************************/
    public ArrayList read_num_of_Presence()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from student_teacher0",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String id = cursor.getString(0);
            String name = cursor.getString(2);

            arrayList.add(id+"/"+name);
            cursor.moveToNext();
        }

        return arrayList;
    }


    public boolean write_Presence(String id,int x)
    {
       x++;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Presence",x);
        sqLiteDatabase.update("student_teacher0",contentValues,"id=?",new String[]{id});
        return  true;
    }


    /************************* write the Absence of student *************************/
    public ArrayList read_num_of_Absence()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from student_teacher0",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String id = cursor.getString(0);
            String name = cursor.getString(3);

            arrayList.add(id+"/"+name);
            cursor.moveToNext();
        }

        return arrayList;
    }


    public boolean write_Absence(String id,int x)
    {
        x++;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Absence",x);
        sqLiteDatabase.update("student_teacher0",contentValues,"id=?",new String[]{id});
        return  true;
    }


    /************************* write the pay of student *************************/
    public ArrayList read_num_of_pay()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from student_teacher0",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String id = cursor.getString(0);
            String name = cursor.getString(4);

            arrayList.add(id+"/"+name);
            cursor.moveToNext();
        }

        return arrayList;
    }


    public boolean write_pay(String id,int x)
    {
        x++;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Pay",x);
        sqLiteDatabase.update("student_teacher0",contentValues,"id=?",new String[]{id});
        return  true;
    }

    /************************* write the pay of nark *************************/
    public ArrayList read_num_of_marks()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from marks0",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            String id = cursor.getString(0);
            String mark = cursor.getString(1);

            arrayList.add(id+"]"+mark);
            cursor.moveToNext();
        }

        return arrayList;
    }

    public boolean write_marks(int id,String mark)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("mark",mark);
        sqLiteDatabase.insert("marks0",null,contentValues);
        return  true;
    }
}
