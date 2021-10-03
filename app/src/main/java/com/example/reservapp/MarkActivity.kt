package com.example.reservapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.data.Company
import com.example.reservapp.fragments.list.Rate
import com.example.reservapp.fragments.list.RateAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_mark.*
import kotlinx.android.synthetic.main.activity_rate.*

class MarkActivity : AppCompatActivity(), RateAdapter.OnItemClickListener {

    private lateinit var rateAdapter: RateAdapter
    private var values = arrayListOf<Rate>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark)
        val item = intent.getParcelableExtra<Company>("toAccept")

        val forLoad = item?.id.toString()

        val savedMail = getSharedPreference("logowanie", "email")
        nazwa_ocen.setText(item?.nazwa)

        loadData(forLoad)
        rateAdapter = RateAdapter(values, this)
        MarkRecycler.adapter = rateAdapter
        MarkRecycler.layoutManager = LinearLayoutManager(this)

    }

    fun Context.getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "${values[position].nick}", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(forLoad: String) {
        val emptyList = Gson().toJson(ArrayList<Rate>())
        val sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(forLoad, emptyList)
        val type = object: TypeToken<ArrayList<Rate>>() {
        }.type

        values = gson.fromJson(json, type)
    }

}