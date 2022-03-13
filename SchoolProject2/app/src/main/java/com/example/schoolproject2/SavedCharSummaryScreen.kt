package com.example.schoolproject2

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.*
import android.print.pdf.PrintedPdfDocument
import android.provider.DocumentsContract
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_saved_char_summary_screen.*
import org.w3c.dom.Document
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random
import kotlin.random.nextInt


class SavedCharSummaryScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_char_summary_screen)


        var printPDFBtn = findViewById<Button>(R.id.button6)
        printPDFBtn.setOnClickListener {
            var builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Print")
            builder.setMessage("Where would you like to print to?")
            builder.setNeutralButton("Back") { dialoginterface, which -> }

            var printPDFDialog = builder.create()
            printPDFDialog.setCancelable(false)
            printPDFDialog.show()
        }


        var viewPDFBtn = findViewById<Button>(R.id.button3)
        viewPDFBtn.setOnClickListener {
            var builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("View")
            builder.setMessage("PDF goes here")
            builder.setNeutralButton("Back") { dialoginterface, which -> }

            var viewPDFDialog = builder.create()
            viewPDFDialog.setCancelable(false)
            viewPDFDialog.show()

            val contentPDF = textView6.text.toString()

            val fileName = Random.nextInt(0..200)

            val fileOutputStream: FileOutputStream

            try {
                fileOutputStream = openFileOutput(fileName.toString(), Context.MODE_PRIVATE)
                fileOutputStream.write(textView6.text.toString().toByteArray())
              
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            showToast("Saved To File!")

        }
    }

    private fun Context.showToast(text:CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,text,duration).show() //Added for file saving
    }

    }











