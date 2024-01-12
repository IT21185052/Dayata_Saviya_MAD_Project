package com.example.a2buttons.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.a2buttons.MainActivitymonthlyexpenditure
import com.example.a2buttons.Models.FormModel
import com.example.a2buttons.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity2Form1 : AppCompatActivity() {

    private lateinit var firstname:EditText
    private lateinit var lastname:EditText
    private lateinit var Email:EditText
    private lateinit var Phone:EditText
    private lateinit var housenumber:EditText
    private lateinit var nooffamilymembers:EditText

    private lateinit var button10 : Button

   private lateinit var dbRef : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2_form1)


        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastName)
        Email = findViewById(R.id.email)
        Phone = findViewById(R.id.phone)
        housenumber = findViewById(R.id.housenumber)
        nooffamilymembers = findViewById(R.id.nooffamilymemmbers)

        button10 = findViewById(R.id.button10)

        dbRef = FirebaseDatabase.getInstance().getReference("FormDetails")

        button10.setOnClickListener{
            saveData()
        }

        val myButton = findViewById<Button>(R.id.button15)
        myButton.setOnClickListener {
            val intent = Intent(this, MainActivity2kyc::class.java)
            startActivity(intent)



        }
        val myButton1 = findViewById<Button>(R.id.button11)
        myButton1.setOnClickListener {
            val intent = Intent(this, MainActivity2menu::class.java)
            startActivity(intent)


        }
        val myButton100 = findViewById<Button>(R.id.button100)
        myButton100.setOnClickListener {
            val intent = Intent(this, MainActivitymonthlyexpenditure::class.java)
            startActivity(intent)


        }


    }
    private fun saveData(){
        val fname = firstname.text.toString()
        val lname = lastname.text.toString()
        val email = Email.text.toString()
        val phone = Phone.text.toString()
        val housnum = housenumber.text.toString()
        val noOfmem = nooffamilymembers.text.toString()

        if (fname.isEmpty()){
            firstname.error = "Please enter First name"
        }
        if (lname.isEmpty()){
            lastname.error = "Please enter Last name"
        }
        if (email.isEmpty()){
            Email.error = "Please enter Email"
        }
        if (phone.isEmpty()){
            Phone.error = "Please enter Phone number"
        }
        if (housnum.isEmpty()){
            housenumber.error = "Please enter House Number"
        }
        if (noOfmem.isEmpty()){
            nooffamilymembers.error = "Please enter members"
        }

        val ID = dbRef.push().key!!

        val formDetail = FormModel(ID,fname,lname,email,phone,housnum,noOfmem)

        dbRef.child(ID).setValue(formDetail)
            .addOnCompleteListener{

                Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_LONG).show()
                firstname.text.clear()
                lastname.text.clear()
                Email.text.clear()
                Phone.text.clear()
                housenumber.text.clear()
                nooffamilymembers.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this,"Erro ${err.message}",Toast.LENGTH_LONG).show()
            }
    }
}




























































//        binding..setOnClickListner{
//            val firstName = binding.firstName.text.toString()
//            val lastName = binding.firstName.text.toString()
//            val email = binding.firstName.text.toString()
//            val phone = binding.firstName.text.toString()
//            val houseNumber = binding.firstName.text.toString()
//            val familyMembers = binding.firstName.text.toString()
//
//
//            var databse = FirebaseDatabase.getInstance().getReference("Users")
//            val User = User(firstName,lastName,email,phone,houseNumber,familyMembers)
//            database.child(userName).setValue(User).addOnSuccessListener {
//
//                binding.firstName.text.clear()
//                binding.lastName.text.clear()
//                binding.email.text.clear()
//                binding.phone.text.clear()
//                binding.houseNumber.text.clear()
//                binding.familyMembers.text.clear()
//
//                Toast.makeText( this, "Successfully saved", Toast.LENGTH_SHORT).show()
//
//            }.addOnFailureListener {
//
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//
//            }
//
//
//        }
//