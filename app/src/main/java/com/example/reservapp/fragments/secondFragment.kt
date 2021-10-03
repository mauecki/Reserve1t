package com.example.reservapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.EventsActivity
import com.example.reservapp.R
import com.example.reservapp.UpdateActivity
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import com.example.reservapp.fragments.list.ReservationAdapter
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*


class secondFragment : Fragment(), ReservationAdapter.OnItemClickListener {

    private lateinit var mCompanyViewModel: CompanyViewModel
    val adapter = ReservationAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val recyclerView = view.rezerwacje_rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val savedMail = getSharedPreference("logowanie", "email")
        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mCompanyViewModel.readYourReservation(savedMail).observe(viewLifecycleOwner, Observer { reservation ->
            adapter.setData(reservation)
        })

        return view
    }

    override fun onItemClick(position: Int) {
        val clickedItem = adapter.getData()[position]
        requireActivity().run{
            val intent = Intent(this, EventsActivity::class.java)
            intent.putExtra("event", clickedItem)
            startActivity(intent)
        }
    }

    private fun getSharedPreference(prefsName: String, key: String): String {
        val preferences = this.requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        preferences.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }
}