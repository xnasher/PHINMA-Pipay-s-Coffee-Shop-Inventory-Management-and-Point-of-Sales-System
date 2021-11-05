package com.example.pos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    internal var db = AccountHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change status bar color
        val window: Window = this@MainActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.yellow)
        //----

        val tf1 = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val tf2 = findViewById<EditText>(R.id.editTextTextPassword2)



        var acc = db.getParticularAccount("0")
        var username:String = "admin"
        var password:String = "admin"
        //go to MainActivity when clicked
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            if (tf1.text.toString().length > 0 && tf2.text.toString().length > 0 ){
                if(tf1.text.toString()==username && tf2.text.toString()==password){
                    Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity,Menu::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@MainActivity, "Incorrect Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this@MainActivity, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }

        })


    }
}