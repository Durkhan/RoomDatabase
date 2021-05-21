package com.example.againroomdatabase.data.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.againroomdatabase.R
import com.example.againroomdatabase.data.User
import com.example.againroomdatabase.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)


        mUserViewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        view.add.setOnClickListener{
            insertDatatodataBase()
        }
        return view
    }

    private fun insertDatatodataBase() {
       val firstName=firstname.text.toString()
        val lastName=lastname.text.toString()
        val age=age.text
        if (inputcheck(firstName,lastName,age)){
            val user=User(0,firstName,lastName,Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Succesfully added!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else
        {
            Toast.makeText(requireContext(),"Please ,fill in gaps",Toast.LENGTH_LONG).show()
        }

    }

   private fun inputcheck(firstName:String, lastName:String, age:Editable):Boolean{
       return !(TextUtils.isEmpty(firstName) && TextUtils.isDigitsOnly(lastName) && TextUtils.isEmpty(age))
   }
}