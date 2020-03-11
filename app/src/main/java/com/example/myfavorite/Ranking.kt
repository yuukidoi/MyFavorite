package com.example.myfavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class Ranking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val intent = this.intent
        val text = intent.getStringExtra("category")
        val textView = findViewById<TextView>(R.id.getItem)
        textView.text = text

        val button = findViewById<Button>(R.id.rankButton)
        button.setOnClickListener{
            val editText = findViewById<EditText>(R.id.getItem)
            insertItem(this, editText.text.toString())
            show()
        }
    }
    private fun show(){
        val texts = queryItem(this)
        val listView = findViewById<ListView>(R.id.ranking_list)
        listView.adapter = ArrayAdapter<String>(this,
            R.layout.category_row, R.id.category_text, texts)
    }
}


