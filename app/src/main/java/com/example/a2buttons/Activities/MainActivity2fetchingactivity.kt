package com.example.a2buttons.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2buttons.Adapters.FormAdapter
import com.example.a2buttons.Models.FormModel
import com.example.a2buttons.R
import com.google.firebase.database.*

class MainActivity2fetchingactivity : AppCompatActivity() {

    private lateinit var memRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var memberList:ArrayList<FormModel>

    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_activity2fetchingactivity)


        memRecyclerView = findViewById(R.id.rvEmp)
        memRecyclerView.layoutManager = LinearLayoutManager(this)
        memRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        memberList = arrayListOf<FormModel>()
        getMemberData()
    }
    private fun getMemberData(){
        memRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("FormDetails")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                memberList.clear()
                if (snapshot.exists()) {
                    for (memSnap in snapshot.children) {
                        val memData = memSnap.getValue(FormModel::class.java)
                        memberList.add(memData!!)
                    }
                    val mAdapter = FormAdapter(memberList)
                    memRecyclerView.adapter = mAdapter



                    //click on listner

                    mAdapter.setOnItemClickListener(object :FormAdapter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            val intent = Intent(this@MainActivity2fetchingactivity,MainActivity2memberDetails::class.java)


                            //put extras


                            intent.putExtra("ID", memberList[position].ID)
                            intent.putExtra("Fname", memberList[position].Fname)
                            intent.putExtra("Lname", memberList[position].Lname)
                            intent.putExtra("Email", memberList[position].Email)
                            intent.putExtra("Phone", memberList[position].Phone)
                            intent.putExtra("NoOfmem", memberList[position].NoOfmem)
                            intent.putExtra("Housenum", memberList[position].Housenum)
                            startActivity(intent)
                        }
                    })

                    memRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE


                }
            }
            override fun onCancelled(error: DatabaseError) {


            }
        })
    }
}







//update



//                    mAdapter.setOnItemClickListener(object : FormAdapter.onItemClickListener{
//                        override fun onItemClick(position: Int) {
//
//                            val intent = Intent(this@MainActivity2fetchingactivity, ActivityMainActivity2MemberdetailsBinding::class.java)
//
//                            //put extras
//                            intent.putExtra("ID", memberList[position].ID)
//                            intent.putExtra("Fname", memberList[position].Fname)
//                            intent.putExtra("Lname", memberList[position].Lname)
//                            intent.putExtra("Email", memberList[position].Email)
//                            intent.putExtra("Phone", memberList[position].Phone)
//                            intent.putExtra("NoOfmem", memberList[position].NoOfmem)
//                            intent.putExtra("Housenum", memberList[position].Housenum)
//
//
//                            startActivity(intent)
//                        }
//
//                    })



