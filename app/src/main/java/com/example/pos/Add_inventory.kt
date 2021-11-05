package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

class Add_inventory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        val tf1 = findViewById<TextInputEditText>(R.id.editTextTextPersonName2)
        val tf2 = findViewById<TextInputEditText>(R.id.editTextTextPersonName3)
        val tf3 = findViewById<TextInputEditText>(R.id.editTextTextPersonName4)
        val tf4 = findViewById<TextInputEditText>(R.id.editTextTextPersonName5)
        val tf5 = findViewById<TextInputEditText>(R.id.editTextTextPersonName6)
        val tf6 = findViewById<TextInputEditText>(R.id.editTextTextPersonName7)
        var db = InventoryHandler(this)
        tf5.isFocusable = false
        tf5.isFocusableInTouchMode = false

        tf3.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                var cost:Float = 0f
                var quantity:Float = 0f
                if(tf3.length()==0 || tf4.length()==0){
                    if (tf3.length()==0){
                        cost=0f
                        tf5.setText("0")
                    }
                    if (tf4.length()==0){
                        quantity=0f
                        tf5.setText("0")
                    }
                }
                else{
                    cost = tf3.text.toString().toFloat()
                    quantity= tf4.text.toString().toFloat()
                    val s = cost*quantity
                    tf5.setText(s.toString())
                }
            }

        })
        tf4.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                var cost:Float = 0f
                var quantity:Float = 0f
                if(tf3.length()==0 || tf4.length()==0){
                    if (tf3.length()==0){
                        cost=0f
                    }
                    if (tf4.length()==0){
                        quantity=0f
                    }
                }
                else{
                    cost = tf3.text.toString().toFloat()
                    quantity= tf4.text.toString().toFloat()
                    val s = cost*quantity
                    tf5.setText(s.toString())
                    tf6.setText(quantity.toString())
                }
            }

        })

        // this is the add button
        val add = findViewById<Button>(R.id.add)
        // set on-click listener
        add.setOnClickListener {
            // your code to perform when the user clicks on the button
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 && tf3.text.toString().length > 0 && tf4.text.toString().length > 0&& tf5.text.toString().length > 0 && tf6.text.toString().length > 0){
                var inventoryClass = InventoryClass(tf1.text.toString(), tf2.text.toString(), tf3.text.toString().toFloat(), tf4.text.toString().toFloat(), tf5.text.toString().toFloat(), tf6.text.toString().toFloat())
                db.insertData(inventoryClass)
                Toast.makeText(this, "Inventory Added", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@Add_inventory, Inventory::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }

        }

        // this is the add inventory button it will go to inventory list activty when clicked
        val listview = findViewById<Button>(R.id.inventoryList)
        listview.setOnClickListener {
            // your code to perform when the user clicks on the Button
            var intent = Intent(this@Add_inventory, Inventory::class.java)
            startActivity(intent)
            finish()
        }
    }
}