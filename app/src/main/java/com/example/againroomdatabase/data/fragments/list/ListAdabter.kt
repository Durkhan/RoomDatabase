package com.example.againroomdatabase.data.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.againroomdatabase.R
import com.example.againroomdatabase.data.User
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class ListAdabter:RecyclerView.Adapter<ListAdabter.ViewHolder>() {
    private var userList=emptyList<User>()

    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentitem=userList[position]
        holder.itemView.id_txt.text=currentitem.id.toString()
        holder.itemView.firstname_txt.text=currentitem.firstName.toString()
        holder.itemView.lastname_txt.text=currentitem.lastName.toString()
        holder.itemView.age_txt.text=currentitem.age.toString()
        holder.itemView.rowlayout.setOnClickListener{
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentitem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setdata(user:List<User>){
        this.userList=user
        notifyDataSetChanged()
    }
}