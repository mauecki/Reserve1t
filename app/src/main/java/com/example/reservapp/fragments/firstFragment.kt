package com.example.reservapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.DetailActivity
import com.example.reservapp.R
import com.example.reservapp.UpdateActivity
import com.example.reservapp.data.CompanyViewModel
import com.example.reservapp.databinding.FragmentFirstBinding
import com.example.reservapp.fragments.list.ListAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*



class firstFragment : Fragment(), ListAdapter.OnItemClickListener {

    private lateinit var mCompanyViewModel: CompanyViewModel
    val adapter = ListAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)


        view.search_btn.setOnClickListener{
            val search = search_et.text.toString()
            searchDatabase(search);
        }
        //recyclerView

        val recyclerView = view.recyclerview_d
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mCompanyViewModel.readAllData.observe(viewLifecycleOwner, Observer { company ->
            adapter.setData(company)
        })
        return view
    }

    override fun onItemClick(position: Int) {
        val clickedItem = adapter.getData()[position]
        requireActivity().run{
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", clickedItem)
            startActivity(intent)
        }
    }


    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"
        mCompanyViewModel.searchCompany(searchQuery).observe(viewLifecycleOwner,{ list ->
            list.let{
                adapter.setData(it)
            }
        })

    }

}