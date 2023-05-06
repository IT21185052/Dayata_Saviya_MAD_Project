package com.example.a2buttons.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.a2buttons.R
import com.example.a2buttons.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {

    //enable the view binding feature
    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference
    private lateinit var fetchdatabutton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)







//fetch button
//        fetchdatabutton = findViewById(R.id.fetchdatabutton)
//
//        fetchdatabutton.setOnClickListener{
//            val intent = Intent(this, MainActivity2fetchingactivity::class.java)
//            startActivity(intent)
//        }
//











       // setContentView(R.layout.activity_main)


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
//                Toast.makeText( this, "Successfully saved",Toast.LENGTH_SHORT).show()
//
//            }.addOnFailureListener {
//
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//
//            }
//
//
//        }



        //button navigation
        val myButton= findViewById<Button>(R.id.nonb1)
        myButton.setOnClickListener {
            val intent = Intent(this, MainActivity2nongovernmentagree::class.java)
            startActivity(intent)
    }


    }
}

private fun <TResult> Task<TResult>.addOnFailureListener(show: Unit) {

}




