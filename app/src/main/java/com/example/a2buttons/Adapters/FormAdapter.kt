package com.example.a2buttons.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2buttons.Models.FormModel
import com.example.a2buttons.R


class FormAdapter(private val memList: ArrayList<FormModel>):
    RecyclerView.Adapter<FormAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener


    //onclick listner

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.member_list_item, parent, false)
        return ViewHolder(itemView,mListener)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = memList[position]
        holder.tvmemberName.text = currentEmp.Fname
        holder.lastName.text = currentEmp.Lname
        holder.id.text = currentEmp.ID

    }



    override fun getItemCount(): Int {
        return memList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvmemberName: TextView = itemView.findViewById(R.id.tvmemberName)
        val lastName: TextView = itemView.findViewById(R.id.LastName)
        val id: TextView = itemView.findViewById(R.id.tvId)


        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}