package com.example.schoolproject2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SavedCharSummaryScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_char_summary_screen)


        var printPDFBtn = findViewById<Button>(R.id.button6)
        printPDFBtn.setOnClickListener{
            var builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Print")
            builder.setMessage( "Where would you like to print to?")
            builder.setNeutralButton("Back"){dialoginterface, which->}

            var printPDFDialog = builder.create()
            printPDFDialog.setCancelable(false)
            printPDFDialog.show()
        }


        var viewPDFBtn = findViewById<Button>(R.id.button3)
        viewPDFBtn.setOnClickListener{
            var builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("View")
            builder.setMessage( "PDF goes here")
            builder.setNeutralButton("Back"){dialoginterface, which->}

            var viewPDFDialog = builder.create()
            viewPDFDialog.setCancelable(false)
            viewPDFDialog.show()












        }

    }

}

