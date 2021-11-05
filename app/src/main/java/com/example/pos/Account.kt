package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Account : AppCompatActivity() {
    internal var db = AccountHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        //change status bar color
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.yellow)

        val tf1 = findViewById<EditText>(R.id.editTextTextPassword)
        val tf2 = findViewById<EditText>(R.id.newpass)
        val tf3 = findViewById<EditText>(R.id.Rnewpass)

        var acc = db.getParticularAccount("0")
        var username:String = "admin"
        var password:String = "admin"



        // this is the password button
        val pass = findViewById<Button>(R.id.changepass)
        val logout = findViewById<Button>(R.id.logout)
        // set on-click listener
        pass.setOnClickListener {
            // your code to perform when the user clicks on the button
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 && tf3.text.toString().length > 0){
                if(tf1.text.toString()==password && tf2.text.toString()==tf3.text.toString()){
                    var accountClass = AccountClass(username, tf2.text.toString())
                    db.changePassword("0",accountClass)
                    Toast.makeText(this@Account, "Change Password Successful", Toast.LENGTH_SHORT).show()
                    tf1.text.clear()
                    tf2.text.clear()
                    tf3.text.clear()
                }
                else{
                    Toast.makeText(this@Account, "Incorrect Details", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this@Account, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        }
        logout.setOnClickListener{
            Toast.makeText(this@Account, "Logout Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Account,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}