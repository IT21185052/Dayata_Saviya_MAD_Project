package com.example.sample1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sample1.R

class UserProfileActivity : AppCompatActivity() {

    private lateinit var btnprofileUpdate : Button
    private lateinit var btnprofileDelete : Button
    private lateinit var btnsignout : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        btnprofileUpdate = findViewById(R.id.btnprofileUpdate)
        btnprofileDelete = findViewById(R.id.btnprofileDelete)
        btnsignout = findViewById(R.id.btnsignout)

        btnprofileUpdate.setOnClickListener{
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }

        btnprofileDelete.setOnClickListener{
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }



    }
}