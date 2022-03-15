package com.example.schoolproject2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_generate_random_screen.*
import kotlinx.android.synthetic.main.activity_main.navView
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateRandomScreen : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle

    // Write a message to the database
    private lateinit var database: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var dataSnapShot: Task<DataSnapshot>
    //private val myRef = database.getReference()
    //private val humanMale = mDatabase.getReference("humanMale")
    //val physicalTraits = mDatabase.getReference("physicalTraits")
    //wants to convert to direct property syntax - ignoring for now / functions fine as is //JES

    private val nameList: MutableList<String> = mutableListOf()
    private val raceList: MutableList<String> = mutableListOf("Human","Elf", "Half Elf", "Gnome", "Dwarf", "Dragonborn", "Halfling")
    private val lifestyleList: MutableList<String> = mutableListOf("Hardworking","Lazy", "Wretched", "Wealthy", "Modest",)
    private val occupationList: MutableList<String> = mutableListOf()
    private val alignmentList: MutableList<String> = mutableListOf()
    private val bondList: MutableList<String> = mutableListOf()
    private val flawList: MutableList<String> = mutableListOf()
    private val idealsList: MutableList<String> = mutableListOf()
    private var physicalTraitsList: MutableList<String> = mutableListOf()
    //val physicalTraitsList: MutableList<String> = mutableListOf("blah blah", "meow", "woof")

    //private val physicalTraitsDatabase: MutableList<String> = database.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        //think about getting the dataSnapshot into an array or list to speed production up
        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("/physicalDatabase")
        dataSnapShot = dbReference.get()
        //query = dbReference.child("physical")
        //query.addValueEventListener()
        //physicalTraitsList = mutableListOf(dataSnapShot.result.children.toString())
        while ((!dataSnapShot.isComplete)) {
            Log.e("Task", "Waiting on Physical")
        }
        for (item in dataSnapShot.result.children) {
            physicalTraitsList.add(item.value.toString())
        }
        dbReference = database.getReference("/flawDatabase")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on Flaws")
        }
        for(item in dataSnapShot.result.children){
            flawList.add(item.value.toString())
        }
        dbReference = database.getReference("/occupationDatabase")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on Occupation")
        }
        for(item in dataSnapShot.result.children){
            occupationList.add(item.value.toString())
        }
        dbReference = database.getReference("/bondDatabase")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on Bond")
        }
        for(item in dataSnapShot.result.children){
            bondList.add(item.value.toString())
        }
        dbReference = database.getReference("/alignmentDatabase")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on Alignment")
        }
        for(item in dataSnapShot.result.children){
            alignmentList.add(item.value.toString())
        }
        dbReference = database.getReference("/anyIdeal")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on Ideal")
        }
        for(item in dataSnapShot.result.children){
            idealsList.add(item.value.toString())
        }
        dbReference = database.getReference("/humanMale")
        dataSnapShot = dbReference.get()
        while((!dataSnapShot.isComplete)){
            Log.e("Task", "Waiting on humanMale")
        }
        for(item in dataSnapShot.result.children){
            nameList.add(item.value.toString())
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_screen)

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

            val randomPhysicalTraits = (0 until(physicalTraitsList.size)).random()
            val physicalInformation: String = physicalTraitsList[randomPhysicalTraits]
            textView40.text = physicalInformation

            try { //method to attempt to stall the crash of program
                super.onDestroy()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
            //val randomHumanMale = (0 until(humanMaleList.size)).random()
//            val randomPhysicalTraits = Random.nextInt(0..98)
//                //(0 until(physicalTraitsList.size)).random()
//                textView40.text = physicalTraitsList[randomPhysicalTraits].toString()
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

        // Read from the database
//        myRef.addValueEventListener(object: ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                val value = snapshot.getValue<String>()
//                if (value != null) {
//                    Log.d(TAG, value)
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//
//        })
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
