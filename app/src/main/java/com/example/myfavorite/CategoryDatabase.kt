package com.example.myfavorite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "CategoryDatabase"
private const val DB_VERSION = 1

class CategoryDatabaseOpenHelper(context: Context) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        //table
        db?.execSQL("CREATE TABLE categories (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "text Text NOT NULL," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //バージョン更新時のSQL発行
    }
}

fun queryCategory(context: Context) : List<String> {
    //読み込みようのデータベース
    val database = CategoryDatabaseOpenHelper(context).readableDatabase
    //全件検索
    val cursor = database.query(
        "categories", null, null, null, null, null, "created_at DESC"
    )

    val categories = mutableListOf<String>()
    cursor.use {
        //カーソルで順次処理
        while (cursor.moveToNext()) {
            val text = cursor.getString(cursor.getColumnIndex("text"))
            categories.add(text)
        }
    }
    database.close()
    return categories
}


//categoriesテーブルにレコードを挿入する
fun insertText(context: Context, text: String){
    val datadase = CategoryDatabaseOpenHelper(context).writableDatabase

    datadase.use { db->
        val record = ContentValues().apply {
            put("text", text)
        }
        db.insert("categories", null , record)
    }
}
