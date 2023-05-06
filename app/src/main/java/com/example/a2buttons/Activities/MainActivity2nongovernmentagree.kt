package com.example.a2buttons.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a2buttons.R


class MainActivity2nongovernmentagree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2nongovernmentagree)

        val myButton1= findViewById<Button>(R.id.ab1)
        myButton1.setOnClickListener {
            val intent = Intent(this, MainActivity2menu::class.java)
            startActivity(intent)


        }
        val myButton2 = findViewById<Button>(R.id.nonb1)
        myButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }


    }
}