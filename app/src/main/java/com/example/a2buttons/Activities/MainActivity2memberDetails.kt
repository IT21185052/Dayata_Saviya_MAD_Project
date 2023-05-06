package com.example.a2buttons.Activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.a2buttons.Models.FormModel
import com.example.a2buttons.R
import com.google.firebase.database.FirebaseDatabase


class MainActivity2memberDetails : AppCompatActivity() {

    private lateinit var tvId: TextView
    private lateinit var tvFname: TextView
    private lateinit var tvLname: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvphone: TextView
    private lateinit var tvHousenum: TextView
    private lateinit var tvNoOfmem: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2member_details)

        initView()
        setValuesToViews()


        //update

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("ID").toString(),
                intent.getStringExtra("Fname").toString(),


                )

        }


        //delete


        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("ID").toString()
            )
        }

    }

    private fun deleteRecord(
        id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("FormDetails").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Nongovernment  data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2fetchingactivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    //click on listner


    private fun initView() {
        tvId = findViewById(R.id.tvId)
        tvFname = findViewById(R.id.tvFname)
        tvLname = findViewById(R.id.tvLname)
        tvEmail = findViewById(R.id.tvemail)
        tvphone = findViewById(R.id.tvphone)
        tvHousenum = findViewById(R.id.tvHousenum)
        tvNoOfmem = findViewById(R.id.tvNoOfmem)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvId.text = intent.getStringExtra("ID")
        tvFname.text = intent.getStringExtra("Fname")
        tvLname.text = intent.getStringExtra("Lname")
        tvEmail.text = intent.getStringExtra("Email")
        tvphone.text = intent.getStringExtra("Phone")
        tvHousenum.text = intent.getStringExtra("Housenum")
        tvNoOfmem.text = intent.getStringExtra("NoOfmem")

    }


//    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
    id: String,
    fname: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etmFName = mDialogView.findViewById<EditText>(R.id.etmFName)
        val etmLName = mDialogView.findViewById<EditText>(R.id.etmLName)
        val etmPhone = mDialogView.findViewById<EditText>(R.id.etmPhone)
        val etmEmail = mDialogView.findViewById<EditText>(R.id.etmEmail)
        val etmHousenum = mDialogView.findViewById<EditText>(R.id.etmHousenum)
        val etmNoOfmem = mDialogView.findViewById<EditText>(R.id.etmNoOfmem)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etmFName.setText(intent.getStringExtra("Fname").toString())
        etmLName.setText(intent.getStringExtra("Lname").toString())
        etmPhone.setText(intent.getStringExtra("Phone").toString())
        etmEmail.setText(intent.getStringExtra("Email").toString())
        etmHousenum.setText(intent.getStringExtra("Housenum").toString())
        etmNoOfmem.setText(intent.getStringExtra("NoOfmem").toString())


        mDialog.setTitle("Updating $tvFname Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateMemberData(
                id,
                etmFName.text.toString(),
                etmLName.text.toString(),
                etmPhone.text.toString(),
                etmEmail.text.toString(),
                etmHousenum.text.toString(),
                etmNoOfmem.text.toString(),


                )

        Toast.makeText(applicationContext, "Nongoverment member data updated", Toast.LENGTH_LONG).show()


        //we are setting updated data to our textview

            tvFname.text = etmFName.text.toString()
            tvLname.text = etmLName.text.toString()
            tvEmail.text = etmPhone.text.toString()
            tvphone.text = etmEmail.text.toString()
            tvHousenum.text = etmHousenum.text.toString()
            tvNoOfmem.text = etmNoOfmem.text.toString()

        alertDialog.dismiss()

    }
}

    private fun updateMemberData(
        id: String,
        fname: String,
        lname: String,
        phone: String,
        email: String,
        housenum: String,
        noofmem: String,

        ){
        val dbRef = FirebaseDatabase.getInstance().getReference("FormDetails" ).child(id)
        val memInfo = FormModel(id, fname, lname, phone, email,housenum, noofmem)
        dbRef.setValue(memInfo)
    }
}








