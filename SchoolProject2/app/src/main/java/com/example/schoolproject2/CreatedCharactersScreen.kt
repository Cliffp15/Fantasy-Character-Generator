package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_created_characters_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.navView

class CreatedCharactersScreen : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_created_characters_screen)


        toggle = ActionBarDrawerToggle(this, createdCharDrawerLayout, R.string.open, R.string.close)
        createdCharDrawerLayout.addDrawerListener(toggle)
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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    }