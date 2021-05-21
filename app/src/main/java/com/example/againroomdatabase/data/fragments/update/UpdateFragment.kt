package com.example.againroomdatabase.data.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.againroomdatabase.R
import com.example.againroomdatabase.data.User
import com.example.againroomdatabase.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {
    private lateinit var mUserViewModel:UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_update, container, false)

        view.firstname_update.setText(args.currentuser.firstName)
        view.lastname_update.setText(args.currentuser.lastName)
        view.age_update.setText(args.currentuser.age.toString())

         mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_update.setOnClickListener{
            updateitem()
        }
        setHasOptionsMenu(true)
        return view
    }

    fun updateitem(){
        val firstName=firstname_update.text.toString()
        val lastName=lastname_update.text.toString()
        val age=Integer.parseInt(age_update.text.toString())

        if(inputcheck(firstName,lastName,age_update.text)){
            val userupdate=User(args.currentuser.id,firstName,lastName,age)
            mUserViewModel.updateUser(userupdate)
            Toast.makeText(requireContext(),"Updated added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please,fill in all fields",Toast.LENGTH_LONG).show()
        }

    }
    private fun inputcheck(firstName:String, lastName:String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isDigitsOnly(lastName) && TextUtils.isEmpty(age))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete)
            deleteUser()
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder=AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_,_->
                mUserViewModel.deleteUser(args.currentuser)

                Toast.makeText(requireContext(),"Succesfully removed ${args.currentuser.firstName}",Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete ${args.currentuser.firstName}?")
        builder.setMessage("Are sure you want to ${args.currentuser.firstName}?")
        builder.create().show()
    }
}
