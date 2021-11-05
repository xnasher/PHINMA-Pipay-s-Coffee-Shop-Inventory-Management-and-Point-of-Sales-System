package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Sales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        var db = InventoryHandler(this)
        var db2 = TransactionHandler(this)
        val tf1 = findViewById<TextView>(R.id.editTextTextPassword)
        val tf2 = findViewById<TextView>(R.id.editTextTextPassword2)
        val tf3 = findViewById<TextView>(R.id.editTextTextPassword4)
        val tf1value = db2.getTotalTransaction()
        val tf2value = db.getTotalInventory()
        val tf3Value = tf1value-tf2value
        tf1.setText("P"+tf1value.toString())
        tf2.setText("P"+tf2value.toString())
        tf3.setText("P"+tf3Value.toString())



        // this is the add inventory button it will go to add_inventory activity when clicked
        val inventory = findViewById<Button>(R.id.inventoryButton)
        inventory.setOnClickListener {
            // your code to perform when the user clicks on the Button
            var intent = Intent(this@Sales,Inventory::class.java)
            startActivity(intent)
            }



        // this is the  transactList  button it will go to TransactionList activity when clicked
        val transact = findViewById<Button>(R.id.TransactListsales)
        transact.setOnClickListener {
            // your code to perform when the user clicks on the Button
            var intent = Intent(this@Sales, TransactionList::class.java)
            startActivity(intent)

        }

    }
}