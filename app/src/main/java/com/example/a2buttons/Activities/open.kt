package com.example.a2buttons.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.a2buttons.R

class open : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
           },1000)
    }
}