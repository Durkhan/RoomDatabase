package com.example.againroomdatabase.data.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.againroomdatabase.R
import com.example.againroomdatabase.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
  private lateinit var mUserViewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
       val adabter=ListAdabter()
       val recyclerView=view.recyclerView
       recyclerView.adapter=adabter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->adabter.setdata(user) })
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete){
            deleteAllusers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllusers() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteAllUser()

            Toast.makeText(requireContext(),"Succesfully removed everything",
                Toast.LENGTH_LONG).show()
          
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are sure you want to everything?")
        builder.create().show()
    }

}