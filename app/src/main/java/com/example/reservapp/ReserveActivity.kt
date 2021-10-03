package com.example.reservapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import com.example.reservapp.data.Reservation
import com.example.reservapp.fragments.list.ReservationAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_reserve.*
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.*

class ReserveActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, ReservationAdapter.OnItemClickListener {

    var dzien = 0
    var miesiac = 0
    var rok = 0
    var godzina = 0
    var minuta = 0

    var savedDzien = 0
    var savedMiesiac = 0
    var savedRok = 0
    var savedGodzina = 0
    var savedMinuta = 0

    private lateinit var mCompanyViewModel: CompanyViewModel
    val adapter = ReservationAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        val item = intent.getParcelableExtra<Company>("reserveCompany")
        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        val recyclerView = reservationview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        firma_tv.setText(item?.nazwa)
        dscrb_tv.setText(item?.opis)

        if(item?.kategoria=="korepetycje"){
            imageView11.setImageResource(R.drawable.korepetycje)
        }
        else if(item?.kategoria=="mechanika"){
            imageView11.setImageResource(R.drawable.mechanika)
        }
        else if(item?.kategoria=="fryzjerstwo"){
            imageView11.setImageResource(R.drawable.fryzjerstwo)
        }
        else if(item?.kategoria=="kosmetyka"){
            imageView11.setImageResource(R.drawable.kosmetyka)
        }
        else if(item?.kategoria=="informatyka"){
            imageView11.logo_rv.setImageResource(R.drawable.informatyka)
        }
        else if(item?.kategoria=="konsultacje"){
            imageView11.setImageResource(R.drawable.konsultacje)
        }
        else if(item?.kategoria=="sprzedaż"){
            imageView11.setImageResource(R.drawable.sprzedaz)
        }
        else if(item?.kategoria=="sport"){
            imageView11.setImageResource(R.drawable.sport)
        }
        else if(item?.kategoria=="zdrowie"){
            imageView11.setImageResource(R.drawable.zdrowie)
        }
        else if(item?.kategoria=="serwis"){
            imageView11.setImageResource(R.drawable.serwis)
        }
        else{
            imageView11.setImageResource(R.drawable.inne)
        }

        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mCompanyViewModel.readCompanyReservation(item!!.id).observe( this, androidx.lifecycle.Observer { reservation ->
            adapter.setData(reservation)
        })

        pickDate()
        pickTime()

        apply_btn.setOnClickListener{
            val data = termin_tv.text.toString()
            val godzina = godzina_tv.text.toString()
        if((godzina=="Wybierz godzinę") or (data=="Wybierz termin")){
            Toast.makeText(this, "Wybierz termin oraz godzinę", Toast.LENGTH_SHORT).show()
        }
            else{
                val nazwaFirmy = item?.nazwa.toString()
                val idFirmy = item.id!!.toInt()
                val mailOwner = item.mail
                val miasto = item.miasto
                val ulica = item.ulica
                val telefon = item.telefon
                val kategoria = item.kategoria
                val savedMail = getSharedPreference("logowanie", "email")
                //data
                //godzina
                val dodatkowe = dodatkowe_et.text.toString()
            val reservation = Reservation(0,nazwaFirmy,idFirmy, savedMail, data, godzina, dodatkowe, mailOwner ,0, miasto, ulica, telefon, kategoria)
            mCompanyViewModel.addReservation(reservation)
            Toast.makeText(this, "Pomyślnie dodano", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun pickDate(){
            termin_btn.setOnClickListener{
            getDateCalendar()
            DatePickerDialog(this, this, rok, miesiac, dzien).show()
        }
    }

    private fun pickTime(){
        godzina_btn.setOnClickListener{
        getTimeCalendar()
        TimePickerDialog(this, this, godzina, minuta, true).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDzien = dayOfMonth
        savedMiesiac = month
        savedRok = year
        getDateCalendar()

        termin_tv.text="${addZero(savedDzien)}.${addZero(savedMiesiac)}.${savedRok}"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedGodzina = hourOfDay
        savedMinuta = minute
        getTimeCalendar()
        godzina_tv.text="${addZero(savedGodzina)}:${addZero(savedMinuta)}"
    }

    private fun getDateCalendar(){
        val cal = Calendar.getInstance()
        dzien = cal.get(Calendar.DAY_OF_MONTH)
        miesiac = cal.get(Calendar.MONTH)
        rok = cal.get(Calendar.YEAR)
    }

    private fun getTimeCalendar(){
        val cal = Calendar.getInstance()
        godzina = cal.get(Calendar.HOUR)
        minuta = cal.get(Calendar.MINUTE)
    }

    fun addZero(liczba: Int): String {
        var wZero: String =""
        if(liczba<10){
            wZero = "0${liczba}"
        }
        else{
            wZero="${liczba}"
        }
        return wZero
    }

    fun Context.getSharedPreference(prefsName: String, key: String): String {
        getSharedPreferences(prefsName, Context.MODE_PRIVATE)
            ?.getString(key, "Put your email!")?.let { return it }
        return "Preference doesn't exist."
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, adapter.getData()[position].godzina, Toast.LENGTH_SHORT).show()
    }

}