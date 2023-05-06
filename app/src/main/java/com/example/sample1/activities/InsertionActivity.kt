package com.example.sample1.activities

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

    private lateinit var etBankName: EditText
    private lateinit var etAccountHolderName: EditText
    private lateinit var etAccountNumber: EditText
    private lateinit var etBranchName: EditText
    private lateinit var etBranchCode: EditText
    private lateinit var etAccountType: EditText

    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etBankName = findViewById(R.id.etBankName)//ndv
        etAccountHolderName = findViewById(R.id.etAccountHolderName)
        etAccountNumber = findViewById(R.id.etAccountNumber)
        etBranchName = findViewById(R.id.etBranchName)
        etBranchCode = findViewById(R.id.etBranchCode)
        etAccountType = findViewById(R.id.etAccountType)
        btnSaveData = findViewById(R.id.btnSaveData)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener{
            saveEmployeeData()
        }
    }
    private fun saveEmployeeData() {
        //getting values
        val empBankName = etBankName.text.toString()
        val empAccountHolderName = etAccountHolderName.text.toString()
        val empAccountNumber = etAccountNumber.text.toString()
        val empBranchName = etBranchName.text.toString()
        val empBranchCode = etBranchCode.text.toString()
        val empAccountType = etAccountType.text.toString()

        if (empBankName.isEmpty()){
            etBankName.error = "Please enter Bank name"
        }
        if (empAccountHolderName.isEmpty()){
            etAccountHolderName.error = "Please enter Account Holder Name"
        }
        if (empAccountNumber.isEmpty()){
            etAccountNumber.error = "Please enter Account Number"
        }
        if (empBranchName.isEmpty()){
            etBranchName.error = "Please enter Branch Name"
        }
        if (empBranchCode.isEmpty()){
            etBranchCode.error = "Please enter Branch Code"
        }
        if (empAccountType.isEmpty()){
            etAccountType.error = "Please enter AccountType"
        }

        val empId = dbRef.push().key!!

        val employee = EmployeeModel(empId,empBankName,empAccountHolderName,empAccountNumber,empBranchName,empBranchCode)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG ).show()

                etBankName.text.clear()
                etAccountHolderName.text.clear()
                etAccountNumber.text.clear()
                etBranchName.text.clear()
                etBranchCode.text.clear()
                etAccountType.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}