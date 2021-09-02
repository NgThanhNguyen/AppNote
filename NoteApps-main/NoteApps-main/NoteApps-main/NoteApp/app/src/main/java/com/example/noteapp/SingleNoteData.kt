package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.noteapp.fragment.HomeFragment


class SingleNoteData : AppCompatActivity() {
    private lateinit var title : TextView
    private lateinit var content: TextView
    private lateinit var priority: TextView
    private lateinit var backButton : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_note_data)
        init()
        val extras = intent.extras
        if (extras != null) {
            NoteDetails(extras)
        } else {
            finish()
        }
        Back()
    }
    private fun init(){
        title = findViewById(R.id.title_data)
        content = findViewById(R.id.content_data)
        priority = findViewById(R.id.priority_data)
        backButton = findViewById(R.id.back_button)
    }
    private fun Back(){
        backButton.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun NoteDetails(extras: Bundle){
        title.text = extras.getString("title","")
        content.text = extras.getString("content","")
        priority.text = extras.getString("priority","")
    }
}