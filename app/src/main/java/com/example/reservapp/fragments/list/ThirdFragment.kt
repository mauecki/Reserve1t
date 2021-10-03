package com.example.reservapp.fragments.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.AddActivity
import com.example.reservapp.R
import com.example.reservapp.UpdateActivity
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_third.view.*

class thirdFragment : Fragment(), ListAdapter.OnItemClickListener {

    private lateinit var mCompanyViewModel: CompanyViewModel
    val adapter = ListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        //recyclerView
        val savedMail = getSharedPreference("logowanie", "email")
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mCompanyViewModel.yourCompany(savedMail).observe(viewLifecycleOwner, Observer { company ->
            adapter.setData(company)
        })

        view.addCmp_btn.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, AddActivity::class.java))
            }
        }

        view.full_del.setOnClickListener{
            deleteAllCompany(savedMail)
        }

      return view
    }

    private fun deleteAllCompany(savedMail: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setNegativeButton("Nie"){_,_->}
        builder.setPositiveButton("Tak"){_,_->
            mCompanyViewModel.deleteWithCompany(savedMail)
            mCompanyViewModel.deleteAllCompany(savedMail)
            Toast.makeText(requireContext(), "Usunięto twoje firmy", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Usunięcie firm")
        builder.setMessage("Na pewno chcesz usunąć swoje firmy")
        builder.create().show()
    }

    override fun onItemClick(position: Int) {
       val clickedItem:Company = adapter.getData()[position]
        requireActivity().run{
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("firma", clickedItem)
            startActivity(intent)
        }
    }

    private fun getSharedPreference(prefsName: String, key: String): String {
        val preferences = this.requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        preferences.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }

}