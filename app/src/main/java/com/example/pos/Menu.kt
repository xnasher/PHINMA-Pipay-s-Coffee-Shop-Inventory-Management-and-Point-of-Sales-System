package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class Menu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)


        //transaction
        // get reference to ImageView
        val transaction = findViewById(R.id.transaction) as ImageView
        // set on-click listener
        transaction.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            Toast.makeText(this@Menu, "Transaction", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Menu, Transaction::class.java)
            startActivity(intent)
        }

        //inventory
        // get reference to ImageView
        val inventory = findViewById(R.id.inventory) as ImageView
        // set on-click listener
        inventory.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            Toast.makeText(this@Menu, "Inventory", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@Menu, Inventory::class.java)
            startActivity(intent)

        }


        //sales
        // get reference to ImageView
        val sales = findViewById(R.id.sales) as ImageView
        // set on-click listener
        sales.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            Toast.makeText(this@Menu, "Sales", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@Menu, Sales::class.java)
            startActivity(intent)


        }

        //account
        // get reference to ImageView
        val account = findViewById(R.id.account) as ImageView
        // set on-click listener
        account.setOnClickListener {
            // your code to perform when the user clicks on the ImageView
            Toast.makeText(this@Menu, "Account", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@Menu, Account::class.java)
            startActivity(intent)


        }
    }
}