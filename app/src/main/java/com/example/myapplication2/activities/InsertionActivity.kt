package com.example.myapplication2.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication2.models.EmployeeModel
import com.example.myapplication2.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etJobTitle: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etTransportMethod: EditText
    private lateinit var etStartingFrom: EditText
    private lateinit var etDestination: EditText
    private lateinit var etIDNumber: EditText
    private lateinit var etHouseholdNumber: EditText
    private lateinit var etDutyNumber: EditText



    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        //ndv
        etFirstName= findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etJobTitle = findViewById(R.id.etJobTitle)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        etTransportMethod = findViewById(R.id.etTransportMethod)
        etStartingFrom = findViewById(R.id.etStartingFrom)
        etDestination = findViewById(R.id.etDestination)
        etIDNumber = findViewById(R.id.etIDNumber)
        etHouseholdNumber = findViewById(R.id.etHouseholdNumber)
        etDutyNumber = findViewById(R.id.etDutyNumber)


        btnSaveData = findViewById(R.id.btnSaveData)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }
    }
    private fun saveEmployeeData() {
        //getting values
        val firstName =  etFirstName.text.toString()
        val lastName =  etLastName.text.toString()
        val jobTitle = etJobTitle.text.toString()
        val email = etEmail.text.toString()
        val phoneNumber =  etPhoneNumber.text.toString()
        val transportMethod = etTransportMethod.text.toString()
        val startingFrom = etStartingFrom.text.toString()
        val destination = etDestination.text.toString()
        val idNumber = etIDNumber.text.toString()
        val householdNumber = etHouseholdNumber.text.toString()
        val dutyNumber = etDutyNumber.text.toString()



        if (firstName.isEmpty()){
            etFirstName.error = "Please enter First Name"
        }
        if (lastName.isEmpty()){
            etLastName.error = "Please enter Last Name"
        }
        if (jobTitle.isEmpty()){
            etJobTitle.error = "Please enter job title"
        }
        if (email.isEmpty()){
            etEmail.error = "Please enter email"
        }
        if (phoneNumber.isEmpty()){
            etPhoneNumber.error = "Please enter Phone Number"
        }
        if (transportMethod.isEmpty()){
            etTransportMethod.error = "Please enter Transport Method"
        }
        if (startingFrom.isEmpty()){
            etStartingFrom.error = "Please enter Starting From"
        }
        if (destination.isEmpty()){
            etDestination.error = "Please enter Destination"
        }
        if (idNumber.isEmpty()){
            etIDNumber.error = "Please enter ID Number"
        }
        if (householdNumber.isEmpty()){
            etHouseholdNumber.error = "Please enter Household Number"
        }
        if (dutyNumber.isEmpty()){
            etDutyNumber.error = "Please enter Duty Number"
        }

        val empId = dbRef.push().key!!

        val employee = EmployeeModel(firstName,lastName,jobTitle,email,phoneNumber,transportMethod,startingFrom,destination,idNumber,householdNumber,dutyNumber)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG ).show()


                etFirstName.text.clear()
                etLastName.text.clear()
                etJobTitle.text.clear()
                etEmail.text.clear()
                etPhoneNumber.text.clear()
                etTransportMethod.text.clear()
                etStartingFrom.text.clear()
                etDestination.text.clear()
                etIDNumber.text.clear()
                etHouseholdNumber.text.clear()
                etDutyNumber.text.clear()


            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}