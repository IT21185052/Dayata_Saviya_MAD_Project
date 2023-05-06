package com.example.sample1.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sample1.R
import com.example.sample1.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var tvUserName: TextView
    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvPassword: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("userName").toString(),
                intent.getStringExtra("firstName").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("userName").toString()
            )
        }

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvUserName = findViewById(R.id.tvUserName)
        tvFirstName = findViewById(R.id.tvFirstName)
        tvLastName = findViewById(R.id.tvLastName)
        tvPassword = findViewById(R.id.tvPassword)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvUserName.text = intent.getStringExtra("userName")
        tvFirstName.text = intent.getStringExtra("firstName")
        tvLastName.text = intent.getStringExtra("lastName")
        tvPassword.text = intent.getStringExtra("password")

    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        empId: String,
        empName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val eUserName = mDialogView.findViewById<EditText>(R.id.etUserName)
        val eFirstName = mDialogView.findViewById<EditText>(R.id.etFirstName)
        val eLastName = mDialogView.findViewById<EditText>(R.id.etLastName)
        val ePassword = mDialogView.findViewById<EditText>(R.id.etPassword)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        eUserName.setText(intent.getStringExtra("userName").toString())
        eFirstName.setText(intent.getStringExtra("firstName").toString())
        eLastName.setText(intent.getStringExtra("lastName").toString())
        ePassword.setText(intent.getStringExtra("password").toString())

        val firstName = ""
        mDialog.setTitle("Updating $firstName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            val userName = ""
            updateEmpData(

                eUserName.text.toString(),
                eFirstName.text.toString(),
                eLastName.text.toString(),
                ePassword.text.toString()
            )
            Toast.makeText(applicationContext, "Employee data updated", Toast.LENGTH_LONG).show()

            tvUserName.text = eUserName.text.toString()
            tvFirstName.text = eFirstName.text.toString()
            tvLastName.text = eLastName.text.toString()
            tvPassword.text = ePassword.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateEmpData(
        userName:String,
        firstName:String,
        lastName:String,
        password:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(userName)
        val empInfo = EmployeeModel(userName, firstName, lastName, password)
        dbRef.setValue(empInfo)
    }
}