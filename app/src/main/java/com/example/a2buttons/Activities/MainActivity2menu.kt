package com.example.a2buttons.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a2buttons.MainActivitycalculate
import com.example.a2buttons.R

class MainActivity2menu : AppCompatActivity() {
    private lateinit var fetchdatabutton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2menu)



        val myButton1 = findViewById<Button>(R.id.submitformbutton)
        myButton1.setOnClickListener {
            val intent = Intent(this, MainActivity2Form1::class.java)
            startActivity(intent)

        }
            val fetchdatabutton = findViewById<Button>(R.id.fetchdatabutton)

            fetchdatabutton.setOnClickListener{
                val intent = Intent(this, MainActivity2fetchingactivity::class.java)
                startActivity(intent)
            }

        val myButtoncal = findViewById<Button>(R.id.calculatedatabutton)
        myButtoncal.setOnClickListener {
            val intent = Intent(this, MainActivitycalculate::class.java)
            startActivity(intent)

        }
        }
    }

