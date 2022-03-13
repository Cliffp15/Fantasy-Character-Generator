package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SettingsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_screen)


        val homeBtn = findViewById<Button>(R.id.button6)
        homeBtn.setOnClickListener(
            View.OnClickListener
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }
}