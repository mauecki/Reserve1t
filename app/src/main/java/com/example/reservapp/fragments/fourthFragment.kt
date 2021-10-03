package com.example.reservapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reservapp.R
import kotlinx.android.synthetic.main.fragment_fourth.*
import kotlinx.android.synthetic.main.fragment_fourth.view.*
import android.content.SharedPreferences
import com.example.reservapp.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase




class fourthFragment : Fragment() {

    var id: String?=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)

        val savedMail = getSharedPreference("logowanie", "email")
        val savedId = getSharedPreference("logowanie", "id")


        view.btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        view.a_mail.text="email: $savedMail"
        view.a_id.text="id: $savedId"
        return view
    }

    private fun getSharedPreference(prefsName: String, key: String): String {
        val preferences = this.requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            preferences.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }

}