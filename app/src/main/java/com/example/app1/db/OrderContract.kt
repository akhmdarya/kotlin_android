package com.example.app1.db

import android.net.Uri
import android.provider.BaseColumns

public class OrderContract {


    abstract class OrderEntry {


        companion object{
            var CONTENT_AUTHORITY="com.example.app1"
            var BASE_URI:Uri=Uri.parse("content://$CONTENT_AUTHORITY")
            var PATH ="ordering"

     var CONTENT_URI:Uri=Uri.withAppendedPath(BASE_URI, PATH)

           var TABLE_NAME="ordering"
            var _ID=BaseColumns._ID
            var COLUMN_NAME="name"
            var COLUMN_QUANTITY="quantity"
            var COLUMN_PRICE="price"
            var COLUMN_EXTRA_SERT="hassert"
        }




    }
}