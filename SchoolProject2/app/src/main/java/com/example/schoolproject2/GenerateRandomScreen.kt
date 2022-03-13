package com.example.schoolproject2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generate_random_screen.*
import kotlinx.android.synthetic.main.activity_main.navView
import kotlinx.android.synthetic.main.save_generated_file.*
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.random.Random
import kotlin.random.nextInt

class GenerateRandomScreen : AppCompatActivity()
{
    lateinit var toggle: ActionBarDrawerToggle

    private val nameList: MutableList<String> = mutableListOf("Alex\n","James\n", "Sarah\n", "Clifton\n", "Emily\n")
    private val raceList: MutableList<String> = mutableListOf("Human\n","Elf\n", "Orc\n", "Gnome\n", "Dwarf\n", "Dragonborn\n")
    private val lifestyleList: MutableList<String> = mutableListOf("Hardworking\n", "Lazy\n", "Wretched\n", "Wealthy\n", "Modest\n")
    private val occupationList: MutableList<String> = mutableListOf("Shop Keeper\n","Fletcher\n", "Blacksmith\n", "Miner\n", "Alchemist\n", "Cobbler\n", "Chef\n", "Stable Master\n" )
    private val alignmentList: MutableList<String> = mutableListOf("Lawful Good\n","Neutral Good\n", "Chaotic Good\n", "Lawful Neutral\n", "True Neutral\n", "Chaotic Neutral\n", "Lawful Evil\n", "Neutral Evil\n", "Chaotic Evil\n")
    private val bondList: MutableList<String> = mutableListOf("Protector\n","Loves Family\n", "Must Be Seen As A Hero\n", "Would Die For Honor\n", "Owes An Unpayable Debt\n")
    private val flawList: MutableList<String> = mutableListOf("Greedy\n","Sucker For Pretty Faces\n", "Gambler\n", "Doesn't Know When To Stop\n", "Bites Off More Than They Can Chew\n")
    private val idealsList: MutableList<String> = mutableListOf("Faith\n","Tradition\n", "Power\n", "Community\n", "Respect\n", "Glory\n", "Honor\n", "Nature\n")
    private val physicalTraitsList: MutableList<String> = mutableListOf("Scar Above Eye\n","Giant Hands\n", "Golden Hair\n", "Crooked Nose\n", "Face Tattoos\n", "Nose Piercing\n")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_screen)

        //showSaveGenDialog()

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
            genAge.text = "$randomAge\n"

            val randomName = (0 until(nameList.size)).random()
            genName.text = nameList[randomName]

            val randomRace = (0 until(raceList.size)).random()
            genRace.text = raceList[randomRace]

            val randomLifestyle = (0 until(lifestyleList.size)).random()
            genLifestyle.text = lifestyleList[randomLifestyle]

            val randomOccupation = (0 until(nameList.size)).random()
            genOccupation.text = occupationList[randomOccupation]

            val randomAlignment = (0 until(raceList.size)).random()
            genAlignment.text = alignmentList[randomAlignment]

            val randomBond = (0 until(bondList.size)).random()
            genBond.text = bondList[randomBond]

            val randomFlaw = (0 until(flawList.size)).random()
            genFlaw.text = flawList[randomFlaw]

            val randomIdeals = (0 until(idealsList.size)).random()
            genIdeals.text = idealsList[randomIdeals]

            val randomPhysicalTraits = (0 until(physicalTraitsList.size)).random()
            genPhysTrait.text = physicalTraitsList[randomPhysicalTraits]

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

        showToast("Saved To File!")
    }


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


//    private fun showSaveGenDialog(){
//        toSaveBtn.setOnClickListener{
//
//            val builder = AlertDialog.Builder(this)
//            val inflater = layoutInflater
//            val dialogLayout = inflater.inflate(R.layout.save_generated_file,null)
//            //val editText = dialogLayout.findViewById<EditText>(R.id.saveGenFile)
//
//                with(builder) {
//                    setTitle("Name Your File")
//                    setPositiveButton("Save") { dialog, which ->
//                        //toSaveBtn.text = editText.text.toString()
//
//                        } catch (e: FileNotFoundException) {
//                            e.printStackTrace()
//                        } catch (e: Exception) {
//                            e.printStackTrace()
//                        }
//
//                        showToast("Saved To File!")
//                    }
//                    setNegativeButton("Cancel") { dialog, which ->
//
//                    }
//
//                    setView(dialogLayout)
//                    show()
//                }
//        }
//    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    fun Context.showToast(text:CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,text,duration).show() //Added for file saving
    }



}