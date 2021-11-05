package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class TransactionList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)
        var db = TransactionHandler(this)

        var listOfTran: ArrayList<TransactionClass> = db.listOfTransaction()

        var listView = findViewById<ListView>(R.id.inventoryListview) as ListView
        var listViewAdapter = TransactionAdapter(this,listOfTran)
        listView.adapter = listViewAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this,Update::class.java)
            intent.putExtra("ADD",false)
            intent.putExtra("ID",listOfTran[position].id)
            startActivity(intent)
            finish()
        }


        // this is the add inventory button it will go to add_inventory activity when clicked
        val add = findViewById<Button>(R.id.addtransaction)
        add.setOnClickListener {
            // your code to perform when the user clicks on the Button
            var intent = Intent(this@TransactionList, Transaction::class.java)
            startActivity(intent)
            finish()
        }
    }
}