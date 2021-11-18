package com.example.app1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast


class DBHelper(
    context: Context?,

) : SQLiteOpenHelper(context, DATABASE_NAME,FACTORY, DATABASE_VERSION) {
    companion object{
        var DATABASE_VERSION=1
        var DATABASE_NAME="usersDB"
        var TABLE_USERS="user"
        var KEY_ID="id"
        var KEY_NAME="name"
        var KEY_MAIL="mail"
        var KEY_PASSWORD="password"
        var FACTORY = null
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table "+ TABLE_USERS+"("+ KEY_ID+" integer primary key autoincrement,"
                + KEY_NAME+ " text,"+ KEY_MAIL+" text,"+  KEY_PASSWORD+ " text"+")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists "+ TABLE_USERS)
        onCreate(db)
    }

    fun insertUserData(name:String, mail:String,password:String):Long{
        val db:SQLiteDatabase=writableDatabase
        val values: ContentValues= ContentValues()
        values.put("name",name)
        values.put("mail",mail)
        values.put("password",password)
       val success= db.insert("user",null, values)
        db.close()
        return success

    }

    fun userIsInDB(login_mail: String,login_password: String):Boolean{
        Log.d("INFO!!!!!!!!!!!", login_mail + login_password)
        val db:SQLiteDatabase=writableDatabase
        val query="SELECT * FROM user where mail='$login_mail' and password='$login_password'"
       val cursor=db.rawQuery(query ,null)
        if (cursor.count>0){
            Log.d("INFO count", cursor.count.toString())

            return false
        }
        else {
            Log.d("INFO", cursor.count.toString())
            return true
        }
        cursor.close()
    }


    @SuppressLint("Range")
    fun viewUsers(): ArrayList<EmpModelClass> {

        val empList: ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()

        // Query to select all the records from the table.
        val selectQuery = "SELECT * FROM user"


        val db = this.readableDatabase
        // Cursor is used to read the record one by one. Add them to data model class.
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)

        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var mail: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                mail = cursor.getString(cursor.getColumnIndex(KEY_MAIL))

                val emp = EmpModelClass( id=id,name = name, mail = mail)
                empList.add(emp)

            } while (cursor.moveToNext())
        }

        return empList
    }


    fun updateUser(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name) // EmpModelClass Name
        contentValues.put(KEY_MAIL, emp.mail) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_USERS, contentValues, KEY_ID + "=" + emp.id, null)


        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }


    fun deleteUser(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_USERS, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }



}