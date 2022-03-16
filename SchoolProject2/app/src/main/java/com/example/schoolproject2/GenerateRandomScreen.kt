package com.example.schoolproject2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generate_random_screen.*
import kotlinx.android.synthetic.main.activity_main.navView
import kotlinx.android.synthetic.main.save_generated_file.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateRandomScreen : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    // Write a message to the database
    private lateinit var database: FirebaseDatabase
    private lateinit var dbReference: DatabaseReference
    private lateinit var dataSnapShot: Task<DataSnapshot>

    //region **Initialize Lists
    private val genderList: MutableList<String> = mutableListOf("Male", "Female", "Non-Binary", "Male", "Female", "Male", "Female")
    private val humanMaleList: MutableList<String> = mutableListOf() //list of names for Human men
    private val humanFemaleList: MutableList<String> =
        mutableListOf() //list of names for Human women
    private val humanSurnameList: MutableList<String> =
        mutableListOf() //list of last names for Humans
    private val dwarfMaleList: MutableList<String> = mutableListOf() //list of names for Dwarven men
    private val dwarfFemaleList: MutableList<String> =
        mutableListOf() //list of names for Dwarven women
    private val dwarfSurnameList: MutableList<String> =
        mutableListOf() //list of last names for Dwarves
    private val elfMaleList: MutableList<String> = mutableListOf() //list of names for Elven men
    private val elfFemaleList: MutableList<String> = mutableListOf() //list of names for Elven women
    private val elfSurnameList: MutableList<String> = mutableListOf() //list of last names for Elves
    private val halfelfMaleList: MutableList<String> =
        mutableListOf() //list of names for half elven men
    private val halfelfFemaleList: MutableList<String> =
        mutableListOf() //list of names for half elven women
    private val gnomeMaleList: MutableList<String> = mutableListOf() //list of names for gnome men
    private val gnomeFemaleList: MutableList<String> =
        mutableListOf() //list of names for gnome women
    private val dragonbornMaleList: MutableList<String> =
        mutableListOf() //list of names for dragonborn men
    private val dragonbornFemaleList: MutableList<String> =
        mutableListOf() //list of names for dragonborn women
    private val halflingMaleList: MutableList<String> =
        mutableListOf() //list of names for halfling men
    private val halflingFemaleList: MutableList<String> =
        mutableListOf() //list of names for halfling women
    private val halflingSurnameList: MutableList<String> =
        mutableListOf() //list of last names for halflings
    private val raceList: MutableList<String> =
        mutableListOf("Human", "Elf", "Half Elf", "Gnome", "Dwarf", "Dragonborn", "Halfling")
    private val lifestyleList: MutableList<String> =
        mutableListOf("Wretched", "Squalid", "Poor", "Modest", "Comfortable", "Wealthy", "Aristocratic")
    private val occupationList: MutableList<String> =
        mutableListOf() //list of occupations (NEED TO EXPAND)
    private val alignmentList: MutableList<String> = mutableListOf() //list of alignments
    private val bondList: MutableList<String> = mutableListOf() //list of bonds
    private val flawList: MutableList<String> = mutableListOf() // list of flaws
    private val anyIdealList: MutableList<String> =
        mutableListOf() //list of ideals that any character can relate to
    private val goodIdealList: MutableList<String> =
        mutableListOf() //list of ideals that only good characters can relate to
    private val chaoticIdealList: MutableList<String> =
        mutableListOf() //list of ideals that only chaotic characters can relate to
    private val lawfulIdealList: MutableList<String> =
        mutableListOf() //list of ideals that only lawful characters can relate to
    private val neutralIdealList: MutableList<String> =
        mutableListOf() //list of ideals that only neutral characters can relate to
    private val evilIdealList: MutableList<String> =
        mutableListOf() //list of ideals that only evil characters can relate to
    private var physicalTraitsList: MutableList<String> =
        mutableListOf() //list of physical traits to identify the character
//endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        //region ** Database Information Dump **
        //think about getting the dataSnapshot into an array or list to speed production up =+= ADVICE FROM ELLIOT
        database = FirebaseDatabase.getInstance() //get instance of Firebase Database, store in "database"
        dbReference = database.getReference("/physicalDatabase") //refer to the Firebase Database, store in "dbReference"
        //Thread.sleep(2_000)
        dataSnapShot = dbReference.get() //get "snapshot" task data from Firebase database
        //Thread.sleep(2_000)
        while ((!dataSnapShot.isComplete)) { //necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on Physical")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            physicalTraitsList.add(item.value.toString())
        }
        dbReference = database.getReference("/flawDatabase")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on Flaws")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            flawList.add(item.value.toString())
        }
        dbReference = database.getReference("/occupationDatabase")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on Occupation")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            occupationList.add(item.value.toString())
        }
        dbReference = database.getReference("/bondDatabase")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on Bond")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            bondList.add(item.value.toString())
        }
        dbReference = database.getReference("/alignmentDatabase")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on Alignment")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            alignmentList.add(item.value.toString())
        }
        dbReference = database.getReference("/anyIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on anyIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            anyIdealList.add(item.value.toString())
        }
        dbReference = database.getReference("/goodIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on goodIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            goodIdealList.add(item.value.toString())
        }
        dbReference = database.getReference("/lawfulIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on lawfulIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            lawfulIdealList.add(item.value.toString())
        }
        dbReference = database.getReference("/neutralIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on neutralIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            neutralIdealList.add(item.value.toString())
        }
        dbReference = database.getReference("/chaoticIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on chaoticIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            chaoticIdealList.add(item.value.toString())
        }
        dbReference = database.getReference("/evilIdeal")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on evilIdeal")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            evilIdealList.add(item.value.toString())
        }
        //region ** Name Database Dump **
        dbReference = database.getReference("/humanMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on humanMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            humanMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/humanFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on humanFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            humanFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/humanSurname")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on humanSurname")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            humanSurnameList.add(item.value.toString())
        }
        dbReference = database.getReference("/dwarfMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on dwarfMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            dwarfMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/dwarfFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on dwarfFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            dwarfFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/dwarfSurname")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on dwarfSurname")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            dwarfSurnameList.add(item.value.toString())
        }
        dbReference = database.getReference("/elfMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on elfMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            elfMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/elfFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on elfFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            elfFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/elfSurname")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on elfSurname")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            elfSurnameList.add(item.value.toString())
        }
        dbReference = database.getReference("/halflingMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on halflingMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            halflingMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/halflingFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on halflingFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            halflingFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/halflingSurname")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on halflingSurname")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            halflingSurnameList.add(item.value.toString())
        }
        dbReference = database.getReference("/gnomeMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on gnomeMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            gnomeMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/gnomeFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on gnomeFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            gnomeFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/halfelfMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on halfelfMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            halfelfMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/halfelfFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on halfelfFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
        halfelfFemaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/dragonbornMale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on dragonbornMale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            dragonbornMaleList.add(item.value.toString())
        }
        dbReference = database.getReference("/dragonbornFemale")
        dataSnapShot = dbReference.get()
        while ((!dataSnapShot.isComplete)) {//necessary to load the database into the app, **CRASHES WITHOUT**
            Log.e("Task", "Waiting on dragonbornFemale")
        }
        for (item in dataSnapShot.result.children) { //load information from specific database into the proper List of items
            dragonbornFemaleList.add(item.value.toString())
        }
        //endregion
        //endregion

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_screen)

        toggle = ActionBarDrawerToggle(this,genCharDrawerLayout, R.string.open, R.string.close)
        genCharDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val generateBtn = findViewById<Button>(R.id.button2)
        generateBtn.setOnClickListener(View.OnClickListener
        {
            val randomGender =
                (0 until (genderList.size)).random() //choose random index for genderList
            val characterGender: String = genderList[randomGender]
            val randomRace = (0 until (raceList.size)).random() //choose random index for raceList
            val characterRace: String = raceList[randomRace]
            genRace.text = ("$characterGender $characterRace")



            //region ** Characters **
            if (characterRace == "Human") { //if the character is human :
                val randomHumanSurname = (0 until (humanSurnameList.size)).random()
                if (characterGender != "Female") { //if the character is male, or non-binary:
                    val randomHumanMale =
                        (0 until (humanMaleList.size)).random() //choose random number for humanMale
                    val characterName: String =
                        humanMaleList[randomHumanMale] + " " + humanSurnameList[randomHumanSurname] //first + last
                    genName.text = characterName
                } else if (characterGender != "Male") { //if the character is female, or non-binary
                    val randomHumanFemale =
                        (0 until (humanFemaleList.size)).random() //choose random number for humanFemale
                    val characterName: String =
                        humanFemaleList[randomHumanFemale] + " " + humanSurnameList[randomHumanSurname]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..99) //choose random number for age
                genAge.text = "$randomAge"
            } //END HUMAN CONDITIONAL
            else if (characterRace == "Dwarf") {
                val randomDwarfSurname = (0 until (dwarfSurnameList.size)).random()
                if (characterGender != "Female") {
                    val randomDwarfMale =
                        (0 until (dwarfMaleList.size)).random() //choose random number for dwarfMale
                    val characterName: String =
                        dwarfMaleList[randomDwarfMale] + " " + dwarfSurnameList[randomDwarfSurname]
                    genName.text = characterName
                } else if (characterGender != "Male") {
                    val randomDwarfFemale =
                        (0 until (dwarfFemaleList.size)).random() //choose random number for dwarfFemale
                    val characterName: String =
                        dwarfFemaleList[randomDwarfFemale] + " " + dwarfSurnameList[randomDwarfSurname]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..399) //choose random number for age
                genAge.text = "$randomAge"
            } //END DWARF CONDITIONAL
            else if (characterRace == "Elf") {
                val randomElfSurname = (0 until (elfSurnameList.size)).random()
                if (characterGender != "Female") {
                    val randomElfMale =
                        (0 until (elfMaleList.size)).random() //choose random number for elfMale
                    val characterName: String =
                        elfMaleList[randomElfMale] + " " + elfSurnameList[randomElfSurname]
                    genName.text = characterName
                } else if (characterGender != "Male") {
                    val randomElfFemale =
                        (0 until (elfFemaleList.size)).random() //choose random number for elfFemale
                    val characterName: String =
                        elfFemaleList[randomElfFemale] + " " + elfSurnameList[randomElfSurname]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..699) //choose random number for age
                genAge.text = "$randomAge"
            } //END ELF CONDITIONAL
            else if (characterRace == "Halfling") {
                val randomHalflingSurname = (0 until (halflingSurnameList.size)).random()
                if (characterGender != "Female") {
                    val randomHalflingMale =
                        (0 until (halflingMaleList.size)).random() //choose random number for halflingMale
                    val characterName: String =
                        halflingMaleList[randomHalflingMale] + " " + halflingSurnameList[randomHalflingSurname]
                    genName.text = characterName
                } else if (characterGender != "Male") {
                    val randomHalflingFemale =
                        (0 until (halflingFemaleList.size)).random() //choose random number for halflingFemale
                    val characterName: String =
                        halflingFemaleList[randomHalflingFemale] + " " + halflingSurnameList[randomHalflingSurname]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..139) //choose random number for age
                genAge.text = "$randomAge"
            } //END HALFLING CONDITIONAL
            else if(characterRace == "Gnome"){
                if(characterGender != "Female"){
                    val randomGnomeMale = (0 until (gnomeMaleList.size)).random()
                    val characterName: String = gnomeMaleList[randomGnomeMale]
                    genName.text = characterName
                }else if(characterGender != "Male"){
                    val randomGnomeFemale = (0 until (gnomeFemaleList.size)).random()
                    val characterName: String = gnomeFemaleList[randomGnomeFemale]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..449) //choose random number for age
                genAge.text = "$randomAge"
            } //END GNOME CONDITIONAL
            else if(characterRace == "Dragonborn"){
                if(characterGender != "Female"){
                    val randomDragonbornMale = (0 until (dragonbornMaleList.size)).random()
                    val characterName: String = dragonbornMaleList[randomDragonbornMale]
                    genName.text = characterName
                }else if(characterGender != "Male"){
                    val randomDragonbornFemale = (0 until (dragonbornFemaleList.size)).random()
                    val characterName: String = dragonbornFemaleList[randomDragonbornFemale]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..65) //choose random number for age
                genAge.text = "$randomAge"
            } //END DRAGONBORN CONDITIONAL
            else if(characterRace == "Half Elf"){
                if(characterGender != "Female"){
                    val randomHalfelfMale = (0 until (halfelfMaleList.size)).random()
                    val characterName: String = halfelfMaleList[randomHalfelfMale]
                    genName.text = characterName
                }else if(characterGender != "Male"){
                    val randomHalfelfFemale = (0 until (halfelfFemaleList.size)).random()
                    val characterName: String = halfelfFemaleList[randomHalfelfFemale]
                    genName.text = characterName
                }
                val randomAge = Random.nextInt(15..149) //choose random number for age
                genAge.text = "$randomAge"
            }//END HALF ELF CONDITIONAL

            //endregion

            val randomAlignment = (0 until (raceList.size)).random() //choose random number for alignment
            val generatedAlignment = alignmentList[randomAlignment]
            genAlignment.text = generatedAlignment
            //region **IDEALS**
            val randomLawfulIdeal = (0 until (lawfulIdealList.size)).random()
            val randomGoodIdeal = (0 until (goodIdealList.size)).random()
            val randomNeutralIdeal = (0 until (neutralIdealList.size)).random()
            val randomChaoticIdeal = (0 until (chaoticIdealList.size)).random()
            val randomEvilIdeal = (0 until (evilIdealList.size)).random()
            val randomAnyIdeal = (0 until (anyIdealList.size)).random()
            val random0to2 = (0 .. 2).random()
            if(generatedAlignment == "Lawful Good"){
                if(random0to2 == 0) {
                    val generatedIdeal = lawfulIdealList[randomLawfulIdeal]
                    genIdeals.text = generatedIdeal
                }else if(random0to2 == 1){
                    val generatedIdeal = goodIdealList[randomGoodIdeal]
                    genIdeals.text = generatedIdeal
                }else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            } else if(generatedAlignment == "Lawful Neutral"){
                if(random0to2 == 0) {
                    val generatedIdeal = lawfulIdealList[randomLawfulIdeal]
                    genIdeals.text = generatedIdeal
                }else if(random0to2 == 1){
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                }else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Lawful Evil"){
                if(random0to2 == 0) {
                    val generatedIdeal = lawfulIdealList[randomLawfulIdeal]
                    genIdeals.text = generatedIdeal
                }else if(random0to2 == 1){
                    val generatedIdeal = evilIdealList[randomEvilIdeal]
                    genIdeals.text = generatedIdeal
                }else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Neutral Good"){
                if(random0to2 == 0) {
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                }else if(random0to2 == 1){
                    val generatedIdeal = goodIdealList[randomGoodIdeal]
                    genIdeals.text = generatedIdeal
                }else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Neutral"){
                if(random0to2 == 0) {
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                }else if(random0to2 == 1){
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                }else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Neutral Evil") {
                if (random0to2 == 0) {
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                } else if (random0to2 == 1) {
                    val generatedIdeal = evilIdealList[randomEvilIdeal]
                    genIdeals.text = generatedIdeal
                } else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Chaotic Good") {
                if (random0to2 == 0) {
                    val generatedIdeal = chaoticIdealList[randomChaoticIdeal]
                    genIdeals.text = generatedIdeal
                } else if (random0to2 == 1) {
                    val generatedIdeal = goodIdealList[randomGoodIdeal]
                    genIdeals.text = generatedIdeal
                } else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else if(generatedAlignment == "Chaotic Neutral") {
                if (random0to2 == 0) {
                    val generatedIdeal = chaoticIdealList[randomChaoticIdeal]
                    genIdeals.text = generatedIdeal
                } else if (random0to2 == 1) {
                    val generatedIdeal = neutralIdealList[randomNeutralIdeal]
                    genIdeals.text = generatedIdeal
                } else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }else {
                if (random0to2 == 0) {
                    val generatedIdeal = chaoticIdealList[randomChaoticIdeal]
                    genIdeals.text = generatedIdeal
                } else if (random0to2 == 1) {
                    val generatedIdeal = evilIdealList[randomEvilIdeal]
                    genIdeals.text = generatedIdeal
                } else {
                    val generatedIdeal = anyIdealList[randomAnyIdeal]
                    genIdeals.text = generatedIdeal
                }
            }
            //endregion
            val randomLifestyle = (0 until (lifestyleList.size)).random() //choose random number for lifestyle
            genLifestyle.text = lifestyleList[randomLifestyle]

            val randomOccupation = (0 until (occupationList.size)).random() //choose random number for occupation
            genOccupation.text = occupationList[randomOccupation]



            val randomBond = (0 until (bondList.size)).random() //choose random number for bond
            genBond.text = bondList[randomBond]

            val randomFlaw = (0 until (flawList.size)).random() //choose random number for flaw
            genFlaw.text = flawList[randomFlaw]


            val randomPhysicalTraits = (0 until (physicalTraitsList.size)).random() //choose random number for physical identifier
            val physicalInformation: String = physicalTraitsList[randomPhysicalTraits]
            genPhysTrait.text = physicalInformation
        })



    saveBtn.setOnClickListener {

        val savedName = genName.text.toString()
        val savedAge = genAge.text.toString()
        val savedRace = genRace.text.toString()
        val savedLifestyle = genLifestyle.text.toString()
        val savedOccupation = genOccupation.text.toString()
        val savedAlignment = genAlignment.text.toString()
        val savedBond = genBond.text.toString()
        val savedFlaw = genFlaw.text.toString()
        val savedIdeals = genIdeals.text.toString()
        val savedPhysTrait = genPhysTrait.text.toString()

        val fileName = savedName.dropLast(1) + savedRace.dropLast(1) //For The File Name Inside The Dialog

        val fileOutputStream: FileOutputStream

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(savedName.toByteArray())
            fileOutputStream.write(savedAge.toByteArray())
            fileOutputStream.write(savedRace.toByteArray())
            fileOutputStream.write(savedLifestyle.toByteArray())
            fileOutputStream.write(savedOccupation.toByteArray())
            fileOutputStream.write(savedAlignment.toByteArray())
            fileOutputStream.write(savedBond.toByteArray())
            fileOutputStream.write(savedFlaw.toByteArray())
            fileOutputStream.write(savedIdeals.toByteArray())
            fileOutputStream.write(savedPhysTrait.toByteArray())
        }
        catch (e: FileNotFoundException){
            e.printStackTrace()
        }
        catch (e: Exception){
            e.printStackTrace()
        }

    }


        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
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
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}



