package com.example.schoolproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_generate_random_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.navView
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateRandomScreen : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle

    private val nameList: MutableList<String> = mutableListOf("Alex","James", "Sarah", "Clifton", "Emily")
    private val raceList: MutableList<String> = mutableListOf("Human","Elf", "Orc", "Gnome", "Dwarf", "Dragonborn")
    private val lifestyleList: MutableList<String> = mutableListOf("Hardworking","Lazy", "Wretched", "Wealthy", "Modest",)
    private val occupationList: MutableList<String> = mutableListOf("Shop Keeper","Fletcher", "Blacksmith", "Miner", "Alchemist", "Cobbler", "Chef", "Stable Master" )
    private val alignmentList: MutableList<String> = mutableListOf("Lawful Good","Neutral Good", "Chaotic Good", "Lawful Neutral", "True Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil")
    private val bondList: MutableList<String> = mutableListOf("Protector","Loves Family", "Must Be Seen As A Hero", "Would Die For Honor", "Owes An Unpayable Debt")
    private val flawList: MutableList<String> = mutableListOf("Greedy","Sucker For Pretty Faces", "Gambler", "Doesn't Know When To Stop", "Bites Off More Than They Can Chew")
    private val idealsList: MutableList<String> = mutableListOf("Faith","Tradition", "Power", "Community", "Respect", "Glory", "Honor", "Nature")
    private val physicalTraitsList: MutableList<String> = mutableListOf("Scar Above Eye","Giant Hands", "Golden Hair", "Crooked Nose", "Face Tattoos", "Nose Piercing")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_screen)

//        var lifeStyleBtn = findViewById<Button>(R.id.button5)
//        lifeStyleBtn.setOnClickListener{
//           var builder = android.app.AlertDialog.Builder(this)
//            builder.setTitle("Life Style")
//            builder.setMessage( "Life Style Info Here")
//            builder.setNeutralButton("OK"){dialoginterface, which->}
//
//            var lifeStyleDialog = builder.create()
//            lifeStyleDialog.setCancelable(false)
//            lifeStyleDialog.show()
//        }


        toggle = ActionBarDrawerToggle(this,genCharDrawerLayout, R.string.open, R.string.close)
        genCharDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val generateBtn = findViewById<Button>(R.id.button2)
        generateBtn.setOnClickListener(View.OnClickListener
        {
            val randomAge = Random.nextInt(1..99)
            textView8.text = "$randomAge"

            val randomName = (0 until(nameList.size)).random()
            textView9.text = nameList[randomName]

            val randomRace = (0 until(raceList.size)).random()
            textView10.text = raceList[randomRace]

            val randomLifestyle = (0 until(lifestyleList.size)).random()
            textView34.text = lifestyleList[randomLifestyle]

            val randomOccupation = (0 until(nameList.size)).random()
            textView35.text = occupationList[randomOccupation]

            val randomAlignment = (0 until(raceList.size)).random()
            textView36.text = alignmentList[randomAlignment]

            val randomBond = (0 until(bondList.size)).random()
            textView37.text = bondList[randomBond]

            val randomFlaw = (0 until(flawList.size)).random()
            textView38.text = flawList[randomFlaw]

            val randomIdeals = (0 until(idealsList.size)).random()
            textView39.text = idealsList[randomIdeals]

            val randomPysicalTraits = (0 until(physicalTraitsList.size)).random()
            textView40.text = physicalTraitsList[randomPysicalTraits]

        })




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


    private fun ranGen(){

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}