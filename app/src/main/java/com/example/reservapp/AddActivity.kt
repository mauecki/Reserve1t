package com.example.reservapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.reservation_row.view.*

private lateinit var mCompanyViewModel: CompanyViewModel

class AddActivity : AppCompatActivity(),  AdapterView.OnItemSelectedListener {
    var kategoria: String = "inne"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        operationSpinner.onItemSelectedListener = this

        add_btn.setOnClickListener {
        insertDataToDatabase()
    }
    }

    private fun insertDataToDatabase() {
    val nazwa = nazwa_et.text.toString()
    val wlasciciel = wlasciciel_et.text.toString()
    val telefon = telefon_et.text.toString()
    val miasto = miasto_et.text.toString()
    val ulica = ulica_et.text.toString()
    val opis = opis_et.text.toString()
    val savedMail = getSharedPreference("logowanie", "email")

    if(inputCheck(nazwa, wlasciciel, telefon, miasto, ulica, kategoria, opis)){
        val company = Company(0,nazwa,wlasciciel,telefon, miasto, ulica, kategoria, opis, savedMail)
        mCompanyViewModel.addCompany(company)
        Toast.makeText(this, "Pomyślnie dodano", Toast.LENGTH_SHORT).show()
        finish()
    }else{
        Toast.makeText(this, "Wypełnij wszystkie miejsca!!", Toast.LENGTH_SHORT).show()

    }

    }

    private fun inputCheck(nazwa:String, wlasciciel: String, telefon: String, miasto: String,ulica: String, kategoria: String, opis: String): Boolean{
        return !(TextUtils.isEmpty(nazwa) && TextUtils.isEmpty(wlasciciel) && TextUtils.isEmpty(telefon) && TextUtils.isEmpty(miasto) && TextUtils.isEmpty(ulica)&& TextUtils.isEmpty(kategoria)&& TextUtils.isEmpty(opis) )
    }

    fun Context.getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "Value is empty!")?.let { return it }
        return "Preference doesn't exist."
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (position) {
            0 -> {
                kategoria = "korepetycje"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.korepetycje)
            }
            1 -> {
                kategoria = "mechanika"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.mechanika)
            }
            2 ->{
                kategoria = "fryzjerstwo"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.fryzjerstwo)
            }
            3 ->{
                kategoria = "kosmetyka"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.kosmetyka)
            }
            4->{
                kategoria ="konsultacje"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.konsultacje)
            }
            5->{
                kategoria = "sprzedaż"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.sprzedaz)
            }
            6->{
                kategoria ="sport"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.sport)
            }
            7->{
                kategoria ="zdrowie"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.zdrowie)
            }
            8->{
                kategoria ="serwis"
                kategoria_et.setText(kategoria)
                kategoria_img.setImageResource(R.drawable.serwis)
            }
            else -> {
                kategoria="inne"
                kategoria_et.setText("inne")
                kategoria_img.setImageResource(R.drawable.inne)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}