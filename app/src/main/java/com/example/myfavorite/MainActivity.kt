package com.example.myfavorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val editText = findViewById<EditText>(R.id.editText)
            insertText(this, editText.text.toString())
            show()
        }

        val list = findViewById<ListView>(R.id.category_list)
        list.setOnItemClickListener{adapterView, view, position, id ->
            val intent = Intent(this, Ranking::class.java)
            val text = list.getItemAtPosition(position).toString()
            intent.putExtra("category",text)
            startActivity(intent)
        }

        show()


    }

    private fun show(){
        val texts = queryCategory(this)
        val listView = findViewById<ListView>(R.id.category_list)
        listView.adapter = ArrayAdapter<String>(this,
            R.layout.category_row, R.id.category_text, texts)
    }
}

