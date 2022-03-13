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
import androidx.annotation.RequiresApi
import org.w3c.dom.Document
import java.io.FileOutputStream
import java.io.IOException




class SavedCharSummaryScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_char_summary_screen)


    var printPDFBtn = findViewById<Button>(R.id.button6)
    printPDFBtn.setOnClickListener{
        var builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Print")
        builder.setMessage("Where would you like to print to?")
        builder.setNeutralButton("Back") { dialoginterface, which -> }

        var printPDFDialog = builder.create()
        printPDFDialog.setCancelable(false)
        printPDFDialog.show()
    }


    var viewPDFBtn = findViewById<Button>(R.id.button3)
    viewPDFBtn.setOnClickListener{
        var builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("View")
        builder.setMessage("PDF goes here")
        builder.setNeutralButton("Back") { dialoginterface, which -> }

        var viewPDFDialog = builder.create()
        viewPDFDialog.setCancelable(false)
        viewPDFDialog.show()

    }


// create a new document
    PdfDocument document = new PdfDocument();

// create a page description
    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(100, 100, 1).create();

// start a page
    Page page = document.startPage(pageInfo);

// draw something on the page
    View content = getContentView();
    content.draw(page.getCanvas());

// finish the page
    document.finishPage(page);

// add more pages

// write the document content
    document.writeTo(getOutputStream());

// close the document
    document.close();


}










