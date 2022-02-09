package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateRandomBtn = findViewById<Button>(R.id.button)
        generateRandomBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, GenerateRandomScreen::class.java)
            startActivity(intent)
        })


        val createCharacterBtn = findViewById<Button>(R.id.button2)
        createCharacterBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, CreatedCharactersScreen::class.java)
            startActivity(intent)
        })


        val savedCharacterBtn = findViewById<Button>(R.id.button3)
        savedCharacterBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, SavedCharactersScreen::class.java)
            startActivity(intent)
        })


    }
}