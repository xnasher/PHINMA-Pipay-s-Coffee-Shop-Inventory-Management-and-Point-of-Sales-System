package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Update_inventory : AppCompatActivity() {
    internal var db = InventoryHandler(this)
    var isAdd: Boolean = false;
    var id: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_inventory)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        isAdd = intent.getBooleanExtra("ADD", true);
        id = intent.getIntExtra("ID", 0)

        val tf1 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val tf2 = findViewById<EditText>(R.id.editTextTextPersonName3)
        val tf3 = findViewById<EditText>(R.id.editTextTextPersonName4)
        val tf4 = findViewById<EditText>(R.id.editTextTextPersonName5)
        val tf5 = findViewById<EditText>(R.id.editTextTextPersonName6)
        val tf6 = findViewById<EditText>(R.id.editTextTextPersonName7)

        tf4.isFocusable = false
        tf4.isFocusableInTouchMode = false
        tf5.isFocusable = false
        tf5.isFocusableInTouchMode = false

        tf3.addTextChangedListener(object : TextWatcher {
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
        tf4.addTextChangedListener(object : TextWatcher {
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
                }
            }

        })

        if (!isAdd) {
            var inv = db.getParticularInventory("" + id)
            tf1.setText(inv.itemcode)
            tf2.setText(inv.itemname)
            tf3.setText(inv.cost.toString())
            tf4.setText(inv.quantity.toString())
            tf5.setText(inv.total.toString())
            tf6.setText(inv.stock.toString())
        }



        // this is the update button
        val update = findViewById<Button>(R.id.update)
        // set on-click listener
        update.setOnClickListener {
            // your code to perform when the user clicks on the button
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 && tf3.text.toString().length > 0 && tf4.text.toString().length > 0&& tf5.text.toString().length > 0&& tf6.text.toString().length > 0){
                var inventoryClass = InventoryClass(tf1.text.toString(), tf2.text.toString(), tf3.text.toString().toFloat(), tf4.text.toString().toFloat(), tf5.text.toString().toFloat(), tf6.text.toString().toFloat())
                db.updateData(""+id,inventoryClass)
                Toast.makeText(this@Update_inventory, "Inventory Updated", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@Update_inventory, Inventory::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this@Update_inventory, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }

        }

        // this is the inventory button
        val inventory = findViewById<Button>(R.id.inventoryList)
        // set on-click listener
        inventory.setOnClickListener {
            // your code to perform when the user clicks on the button
            db.deleteData(""+id)
            Toast.makeText(this@Update_inventory, "Inventory Deleted", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@Update_inventory, Inventory::class.java)
            startActivity(intent)
            finish()

        }
    }
}