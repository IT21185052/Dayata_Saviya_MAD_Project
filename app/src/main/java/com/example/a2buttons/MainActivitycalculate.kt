package com.example.a2buttons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivitycalculate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main_activitycalculate)


        val Number1 = findViewById(R.id.num1) as EditText
        val Number2 = findViewById(R.id.num2) as EditText
        val resultTextview = findViewById(R.id.resultView) as TextView
        val calbtn=findViewById(R.id.calbutton) as Button

        calbtn.setOnClickListener{

            val n1 = Number1.text.toString().toInt()
            val n2 = Number2.text.toString().toInt()
            val result =n1/n2+4000
            resultTextview.text = result.toString()
            }
    }



}