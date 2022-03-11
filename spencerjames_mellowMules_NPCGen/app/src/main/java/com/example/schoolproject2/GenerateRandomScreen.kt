package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GenerateRandomScreen : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_screen)

        var lifeStyleBtn = findViewById<Button>(R.id.button5)
        lifeStyleBtn.setOnClickListener{
           var builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Life Style")
            builder.setMessage( "Life Style Info Here")
            builder.setNeutralButton("OK"){dialoginterface, which->}

            var lifeStyleDialog = builder.create()
            lifeStyleDialog.setCancelable(false)
            lifeStyleDialog.show()
        }


        val settingsBtn = findViewById<Button>(R.id.button7)
        settingsBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, SettingsScreen::class.java)
            startActivity(intent)
        })

        val contactBtn = findViewById<Button>(R.id.button9)
        contactBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, CreditsScreen::class.java)
            startActivity(intent)
        })


        val homeBtn = findViewById<Button>(R.id.button6)
        homeBtn.setOnClickListener(View.OnClickListener
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }
}