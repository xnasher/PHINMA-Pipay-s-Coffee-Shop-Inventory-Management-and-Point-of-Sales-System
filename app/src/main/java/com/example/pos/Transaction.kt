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

class Transaction : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        val tf1 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val tf2 = findViewById<EditText>(R.id.editTextTextPersonName3)
        val tf3 = findViewById<EditText>(R.id.editTextTextPersonName4)
        val tf4 = findViewById<EditText>(R.id.editTextTextPersonName5)
        val tf5 = findViewById<EditText>(R.id.editTextTextPersonName6)
        var db = TransactionHandler(this)
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


        var addTransaction = findViewById<Button>(R.id.addtransaction);
        addTransaction.setOnClickListener {
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 && tf3.text.toString().length > 0 && tf4.text.toString().length > 0&& tf5.text.toString().length > 0){
                var transactionClass = TransactionClass(tf1.text.toString(), tf2.text.toString(), tf3.text.toString().toFloat(), tf4.text.toString().toFloat(), tf5.text.toString().toFloat())
                db.insertTransaction(transactionClass)
                Toast.makeText(this, "Transaction Added", Toast.LENGTH_SHORT).show()
                var intent = Intent(this@Transaction, TransactionList::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }

        // this is the update transact button it will go to TransactionList activity when clicked
        val transact = findViewById<Button>(R.id.TransactList)
        transact.setOnClickListener {
            // your code to perform when the user clicks on the Button
            var intent = Intent(this@Transaction, TransactionList::class.java)
            startActivity(intent)
            finish()
        }
    }
}