package com.example.myapplication2.activities



import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication2.R
import com.example.myapplication2.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase
import java.text.ParsePosition

class EmployeeDetailsActivity : AppCompatActivity() {


    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvJobTitle: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var tvTransportMethod: TextView
    private lateinit var tvStartingFrom: TextView
    private lateinit var tvDestination: TextView
    private lateinit var tvIDNumber: TextView
    private lateinit var tvHouseholdNumber: TextView
    private lateinit var tvDutyNumber: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("firstName").toString(),
                intent.getStringExtra("lastName").toString()
            )
        }
        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("firstName").toString()
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

        tvFirstName = findViewById(R.id.tvFirstName)
        tvLastName = findViewById(R.id.tvLastName)
        tvJobTitle = findViewById(R.id.tvJobTitle)
        tvEmail = findViewById(R.id.tvEmail)
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        tvTransportMethod = findViewById(R.id.tvTransportMethod)
        tvStartingFrom = findViewById(R.id.tvStartingFrom)
        tvDestination = findViewById(R.id.tvDestination)
        tvIDNumber = findViewById(R.id.tvIDNumber)
        tvHouseholdNumber = findViewById(R.id.tvHouseholdNumber)
        tvDutyNumber= findViewById(R.id.tvDutyNumber)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvFirstName.text = intent.getStringExtra("firstName")
        tvLastName.text = intent.getStringExtra("lastName")
        tvJobTitle.text = intent.getStringExtra("jobTitle")
        tvEmail.text = intent.getStringExtra("email")
        tvPhoneNumber.text = intent.getStringExtra("phoneNumber")
        tvTransportMethod.text = intent.getStringExtra("transportMethod")
        tvStartingFrom.text = intent.getStringExtra("startingFrom")
        tvDestination.text = intent.getStringExtra("destination")
        tvIDNumber.text = intent.getStringExtra("idNumber")
        tvHouseholdNumber.text = intent.getStringExtra("householdNumber")
        tvDutyNumber.text = intent.getStringExtra("dutyNumber")

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


        val eFirstName = mDialogView.findViewById<EditText>(R.id.etFirstName)
        val eLastName = mDialogView.findViewById<EditText>(R.id.etLastName)
        val eJobTitle = mDialogView.findViewById<EditText>(R.id.etJobTitle)
        val eEmail = mDialogView.findViewById<EditText>(R.id.etEmail)
        val ePhoneNumber = mDialogView.findViewById<EditText>(R.id.etPhoneNumber)
        val eTransportMethod = mDialogView.findViewById<EditText>(R.id.etTransportMethod)
        val eStartingFrom = mDialogView.findViewById<EditText>(R.id.etStartingFrom)
        val eDestination = mDialogView.findViewById<EditText>(R.id.etDestination)
        val eIDNumber = mDialogView.findViewById<EditText>(R.id.etIDNumber)
        val eHouseholdNumber = mDialogView.findViewById<EditText>(R.id.etHouseholdNumber)
        val eDutyNumber = mDialogView.findViewById<EditText>(R.id.etDutyNumber)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)


        eFirstName.setText(intent.getStringExtra("firstName").toString())
        eLastName.setText(intent.getStringExtra("lastName").toString())
        eJobTitle.setText(intent.getStringExtra("jobTitle").toString())
        eEmail.setText(intent.getStringExtra("email").toString())
        ePhoneNumber.setText(intent.getStringExtra("phoneNumber").toString())
        eTransportMethod.setText(intent.getStringExtra("transportMethod").toString())
        eStartingFrom.setText(intent.getStringExtra("startingFrom").toString())
        eDestination.setText(intent.getStringExtra("destination").toString())
        eIDNumber.setText(intent.getStringExtra("idNumber").toString())
        eHouseholdNumber.setText(intent.getStringExtra("householdNumber").toString())
        eDutyNumber.setText(intent.getStringExtra("dutyNumber").toString())






        val firstName = ""
        mDialog.setTitle("Updating $firstName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            val firstName = ""
            updateEmpData(


                eFirstName.text.toString(),
                eLastName.text.toString(),
                eJobTitle.text.toString(),
                eEmail.text.toString(),
                ePhoneNumber.text.toString(),
                eTransportMethod.text.toString(),
                eStartingFrom.text.toString(),
                eDestination.text.toString(),
                eIDNumber.text.toString(),
                eHouseholdNumber.text.toString(),
                eDutyNumber.text.toString()

            )
            Toast.makeText(applicationContext, "Employee data updated", Toast.LENGTH_LONG).show()


            tvFirstName.text = eFirstName.text.toString()
            tvLastName.text = eLastName.text.toString()
            tvJobTitle.text = eJobTitle.text.toString()
            tvEmail.text = eEmail.text.toString()
            tvPhoneNumber.text = ePhoneNumber.text.toString()
            tvTransportMethod.text = eTransportMethod.text.toString()
            tvStartingFrom.text = eStartingFrom.text.toString()
            tvDestination.text = eDestination.text.toString()
            tvIDNumber.text = eIDNumber.text.toString()
            tvHouseholdNumber.text = eHouseholdNumber.text.toString()
            tvDutyNumber.text = eDutyNumber.text.toString()




            alertDialog.dismiss()
        }

    }
    private fun updateEmpData(

        firstName:String,
        lastName:String,
        jobTitle:String,
        email:String,
        phoneNumber:String,
        transportMethod:String,
        startingFrom:String,
        destination:String,
        idNumber:String,
        householdNumber:String,
        dutyNumber:String,



        ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(firstName)
        val empInfo = EmployeeModel(firstName, lastName, jobTitle, email, phoneNumber, transportMethod, startingFrom, destination, idNumber, householdNumber, dutyNumber)
        dbRef.setValue(empInfo)
    }
}
