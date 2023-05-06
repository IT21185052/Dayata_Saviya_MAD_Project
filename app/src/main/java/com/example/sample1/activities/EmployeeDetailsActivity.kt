package com.example.sample1.activities

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

    private lateinit var tvEmpAccountNum: TextView
    private lateinit var tvEmpBankName: TextView
    private lateinit var tvEmpAHN: TextView
    private lateinit var tvEmpBranchName: TextView
    private lateinit var tvEmpBranchCode: TextView
    private lateinit var tvEmpAccountType: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empBankName").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("empId").toString()
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
        tvEmpAccountNum = findViewById(R.id.tvEmpAccountNum)
        tvEmpBankName = findViewById(R.id.tvEmpBankName)
        tvEmpAHN = findViewById(R.id.tvEmpAHN)
        tvEmpBranchName = findViewById(R.id.tvEmpBranchName)
        tvEmpBranchCode = findViewById(R.id.tvEmpBranchCode)
        tvEmpAccountType = findViewById(R.id.tvEmpAccountType)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvEmpAccountNum.text = intent.getStringExtra("empAccountNumber")
        tvEmpBankName.text = intent.getStringExtra("empBankName")
        tvEmpAHN.text = intent.getStringExtra("empAccountHolderName")
        tvEmpBranchName.text = intent.getStringExtra("empBranchName")
        tvEmpBranchCode.text = intent.getStringExtra("empBranchCode")
        tvEmpAccountType.text = intent.getStringExtra("empAccountType")

    }

    private fun openUpdateDialog(
        empId: String,
        empName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etEmpBankName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val etEmpAccountHolderName = mDialogView.findViewById<EditText>(R.id.etEmpAge)
        val etEmpAccountNumber = mDialogView.findViewById<EditText>(R.id.etEmpSalary)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpBankName.setText(intent.getStringExtra("empBankName").toString())
        etEmpAccountHolderName.setText(intent.getStringExtra("empAccountHolderName").toString())
        etEmpAccountNumber.setText(intent.getStringExtra("empAccountNumber").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateEmpData(
                empId,
                etEmpBankName.text.toString(),
                etEmpAccountHolderName.text.toString(),
                etEmpAccountNumber.text.toString()
            )
            Toast.makeText(applicationContext, "Employee data updated", Toast.LENGTH_LONG).show()

            tvEmpBankName.text = etEmpBankName.text.toString()
            tvEmpAHN.text = etEmpAccountHolderName.text.toString()
            tvEmpAccountNum.text = etEmpAccountNumber.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateEmpData(
        id:String,
        BankName:String,
        AccountHolderName:String,
        AccountNumber:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val empInfo = EmployeeModel(id, BankName, AccountHolderName,AccountNumber)
        dbRef.setValue(empInfo)
    }
}