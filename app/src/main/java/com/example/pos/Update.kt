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

class Update: AppCompatActivity() {
    internal var db = TransactionHandler(this)
    var isAdd: Boolean = false;
    var id: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

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
            var tran = db.getParticularTransaction("" + id)
            tf1.setText(tran.code)
            tf2.setText(tran.name)
            tf3.setText(tran.price.toString())
            tf4.setText(tran.quantity.toString())
            tf5.setText(tran.total.toString())
        }

        // this is the update button
        val update = findViewById<Button>(R.id.updateTransact)
        // set on-click listener
        update.setOnClickListener {
            // your code to perform when the user clicks on the button
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 && tf3.text.toString().length > 0 && tf4.text.toString().length > 0&& tf5.text.toString().length > 0){
                var transactionClass = TransactionClass(tf1.text.toString(), tf2.text.toString(), tf3.text.toString().toFloat(), tf4.text.toString().toFloat(), tf5.text.toString().toFloat())
                db.updateTransaction(""+id,transactionClass)
                Toast.makeText(this@Update, "Inventory Updated", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@Update, TransactionList::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this@Update, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }

        // this is the inventory button
        val inventory = findViewById<Button>(R.id.TransactList)
        // set on-click listener
        inventory.setOnClickListener {
            // your code to perform when the user clicks on the button
            db.deleteTransaction(""+id)
            Toast.makeText(this@Update, "Transaction Deleted", Toast.LENGTH_SHORT).show()
            var intent = Intent(this@Update, TransactionList::class.java)
            startActivity(intent)
            finish()

        }
    }
}