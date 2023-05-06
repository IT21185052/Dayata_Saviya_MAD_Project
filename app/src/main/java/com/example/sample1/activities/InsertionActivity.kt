package com.example.sample1.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sample1.models.EmployeeModel
import com.example.sample1.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etUserName: EditText
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPassword: EditText


    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        //ndv
        etUserName = findViewById(R.id.etUserName)
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etPassword = findViewById(R.id.etPassword)

        btnSaveData = findViewById(R.id.btnSaveData)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }


    }
    private fun saveEmployeeData() {
        //getting values
        val userName = etUserName.text.toString()
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val password = etPassword.text.toString()



        if (userName.isEmpty()){
            etUserName.error = "Please enter User Name"
        }
        if (firstName.isEmpty()){
            etFirstName.error = "Please enter First Name"
        }
        if (lastName.isEmpty()){
            etLastName.error = "Please enter Last Name"
        }
        if (password.isEmpty()){
            etPassword.error = "Please enter Password"
        }

        val empId = dbRef.push().key!!

        val employee = EmployeeModel(userName,firstName,lastName,password)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_LONG ).show()

                etUserName.text.clear()
                etFirstName.text.clear()
                etLastName.text.clear()
                etPassword.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }


    }
}