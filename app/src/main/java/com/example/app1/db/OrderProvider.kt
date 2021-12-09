package com.example.app1.db

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.net.Uri
import java.lang.Exception


class OrderProvider : ContentProvider() {

    var ORDER:Int=100
    lateinit var mHelper:DBHelper
    var sUriMatcher: UriMatcher= UriMatcher(UriMatcher.NO_MATCH)
    companion object {



           }


    override fun onCreate(): Boolean {
        sUriMatcher.addURI(OrderContract.OrderEntry.CONTENT_AUTHORITY,OrderContract.OrderEntry.PATH,ORDER)
        mHelper=DBHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
      var database:SQLiteDatabase=mHelper.readableDatabase
        var cursor:Cursor
        var match=sUriMatcher.match(uri)
        cursor = when (match) {
            ORDER -> database.query(
                OrderContract.OrderEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            else -> throw java.lang.IllegalArgumentException("CANT QUERY")
        }
        cursor.setNotificationUri(context?.getContentResolver(),uri)
        return cursor
        }


    override fun getType(uri: Uri): String? {
       return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val name = values!!.getAsString(OrderContract.OrderEntry.COLUMN_NAME)
            ?: throw java.lang.IllegalArgumentException("Name is Required")

        val quantity = values.getAsString(OrderContract.OrderEntry.COLUMN_QUANTITY)
            ?: throw java.lang.IllegalArgumentException("quantity is Required")

        val price = values.getAsString(OrderContract.OrderEntry.COLUMN_PRICE)
            ?: throw java.lang.IllegalArgumentException("price is Required")

        // SINCE WE ARE INSERTING DATA IN DATABASE SO NOW WE ARE WRITING ON DATABASE


        // SINCE WE ARE INSERTING DATA IN DATABASE SO NOW WE ARE WRITING ON DATABASE
        val database = mHelper.writableDatabase
        val id = database.insert(OrderContract.OrderEntry.TABLE_NAME, null, values)

        if (id == -1L) {
            return null
        }
        context!!.contentResolver.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var rowsdeleted:Int
        var database:SQLiteDatabase=mHelper.writableDatabase
        var match=sUriMatcher.match(uri)
        when(match){
            ORDER -> {
                rowsdeleted=database.delete(OrderContract.OrderEntry.TABLE_NAME,selection,selectionArgs)

            }
            else ->{
                throw IllegalArgumentException("CANT delete")
            }
        }
        if (rowsdeleted!=0){
            context?.getContentResolver()?.notifyChange(uri,null)
        }
        return rowsdeleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return   0
    }
}