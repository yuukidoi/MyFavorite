package com.example.myfavorite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "ItemDatabase"
private const val DB_VERSION = 1

class ItemDatabaseOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE items (" +
        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "item TEXT NOT NULL," +
        "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
         }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


fun queryItem(context: Context) : List<String> {
    //読み込みようのデータベース
    val database = ItemDatabaseOpenHelper(context).readableDatabase
    //全件検索
    val cursor = database.query(
        "items", null, null, null, null, null, "created_at DESC"
    )

    val items = mutableListOf<String>()
    cursor.use {
        //カーソルで順次処理
        while (cursor.moveToNext()) {
            val text = cursor.getString(cursor.getColumnIndex("item"))
            items.add(text)
        }
    }
    database.close()
    return items
}


//categoriesテーブルにレコードを挿入する
fun insertItem(context: Context, item: String){
    val datadase = ItemDatabaseOpenHelper(context).writableDatabase

    datadase.use { db->
        val record = ContentValues().apply {
            put("item", item)
        }
        db.insert("items", null , record)
    }
}
