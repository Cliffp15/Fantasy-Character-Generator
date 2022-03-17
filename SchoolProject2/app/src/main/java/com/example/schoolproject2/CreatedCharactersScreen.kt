package com.example.schoolproject2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.schoolproject2.databinding.ActivityCreatedCharactersScreenBinding
import kotlinx.android.synthetic.main.activity_created_characters_screen.*
import kotlinx.android.synthetic.main.activity_main.navView
import com.example.schoolproject2.ui.BottomSheet.IAdapterSelection


class CreatedCharactersScreen : AppCompatActivity() {

    lateinit var binding: ActivityCreatedCharactersScreenBinding

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_created_characters_screen)

        binding = ActivityCreatedCharactersScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, createdCharDrawerLayout, R.string.open, R.string.close)
        createdCharDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        val bondlist: MutableList<String> = ArrayList()

        binding.dropdownBonds.setOnClickListener{val bottomsheet= BottomSheet.instance(bondlist, "Choose your bond", "Custom Bond", object: IAdapterSelection{
            override fun onStringSelected(string: String) {
                binding.dropdownBonds.setText(string)
            }

        })
            bottomsheet.show(supportFragmentManager, BottomSheet::class.toString())
        }

        val flawlist: MutableList<String> = ArrayList()
        binding.dropdownFlaws.setOnClickListener {

            val bottomsheet= BottomSheet.instance(flawlist, "Choose your flaw", "Custom Flaw", object: IAdapterSelection{
                override fun onStringSelected(string: String) {
                    binding.dropdownFlaws.setText(string)
                }
            })
            bottomsheet.show(supportFragmentManager, BottomSheet::class.toString())
        }

        val ideallist: MutableList<String> = ArrayList()
        binding.dropdownIdeals.setOnClickListener {

            val bottomsheet = BottomSheet.instance(ideallist, "Choose your ideal", "Custom Ideal", object: IAdapterSelection{
                override fun onStringSelected(string: String) {
                    binding.dropdownIdeals.setText(string)
                   // binding.dropdownIdeals.text = string
                }

                })
            bottomsheet.show(supportFragmentManager, BottomSheet::class.toString())
        }

        bondlist.add("Custom Bond")
        bondlist.add("I would die to recover an ancient artifact of my faith that was lost long ago.")
        bondlist.add("I will someday get revenge on the corrupt temple hierarchy who branded me a heretic.")
        bondlist.add("I owe me life to the priest who took me in when my parents died.")
        bondlist.add("Everything I do is for the common people.")
        bondlist.add("I will do anything to protect the temple where I served.")
        bondlist.add("I seek to preserve a sacred text that my enemies consider heretical and seek to destroy.")
        bondlist.add("I fleeced the wrong person and must work to ensure that this individual never crosses paths with me or those I care about...")
        bondlist.add("I owe everything to my mentor--a horrible person who's probably rotting in jail somewhere.")
        bondlist.add("Somewhere out there I have a child who doesn't know me. I'm making the world better.")
        bondlist.add("I come from a noble family, and one day I'll reclaim my lands and title from those who stole them from me.")

        flawlist.add("Custom Flaw")
        flawlist.add("I judge others harshly, and myself even more severely.")
        flawlist.add("I put too much trust in those who wield power within my temple's hierarchy.")
        flawlist.add("My piety sometimes leads me to blindly trust those that profess faith in my god.")
        flawlist.add("I am inflexible in my thinking.")
        flawlist.add("I am suspicious of strangers and suspect the worst of them.")
        flawlist.add("Once I pick a goal, I become obsessed with it to the detriment of everything else in my life.")
        flawlist.add("I can't resist a pretty face.")
        flawlist.add("I'm always in debt. I spend my ill-gotten gains on decadent luxuries faster than I bring them in.")
        flawlist.add("I'm convinced that no one could ever fool me in the way I fool others.")
        flawlist.add("I'm too greedy for my own good. I can't resist taking a risk if there's money involved")

        ideallist.add("Custom Ideal")
        ideallist.add("Tradition. The ancient traditions of worship and sacrifice must be preserved and upheld.(Lawful)")
        ideallist.add("Charity. I always try to help those in need, no matter what the personal cost.(Good)")
        ideallist.add("Change. We must help bring about the changes the gods are constantly working in the world.(Chaotic)")
        ideallist.add("Power. I hope to one day rise to the top of my faith 's religious hierarchy.(Lawful)")
        ideallist.add("Aspiration. I seek to prove my self worthy of my god 's favor by matching my actions against his or her teachings.(Any)")
        ideallist.add("Independence. I am a free spirit-- no one tells me what to do.(Chaotic)")
        ideallist.add("Fairness. I never target people who can 't afford to lose a few coins.(Lawful)")
        ideallist.add("Charity. I distribute money I acquire to the people who really need it.(Good)")
        ideallist.add("Creativity. I never run the same con twice.(Chaotic)")
        ideallist.add("Friendship. Material goods come and go.Bonds of friendship last forever.(Good)")

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}