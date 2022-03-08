package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.miItem1 -> {

                    val intent = Intent(this, SettingsScreen::class.java)
                    startActivity(intent)
                }
                R.id.miItem2 -> {

                    val intent = Intent(this, CreditsScreen::class.java)
                    startActivity(intent)
                }
                R.id.miItem3 -> {

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }


        //region Buttons
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
        //endregion

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}