package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reservapp.data.Company
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.custom_row.view.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<Company>("detail")

        id_tv2.setText(item?.id.toString())
        nazwa_tv2.setText(item?.nazwa)
        wlasciciel_tv2.setText(item?.wlascicel)
        telefon_tv2.setText(item?.telefon)
        mail_tv2.setText(item?.mail)
        miasto_tv2.setText(item?.miasto)
        ulica_tv2.setText(item?.ulica)
        kategoria_tv2.setText(item?.kategoria)
        opis_tv2.setText(item?.opis)

        if(item?.kategoria=="korepetycje"){
            imageView.setImageResource(R.drawable.korepetycje)
        }
        else if(item?.kategoria=="mechanika"){
            imageView.setImageResource(R.drawable.mechanika)
        }
        else if(item?.kategoria=="fryzjerstwo"){
            imageView.setImageResource(R.drawable.fryzjerstwo)
        }
        else if(item?.kategoria=="kosmetyka"){
            imageView.setImageResource(R.drawable.kosmetyka)
        }
        else if(item?.kategoria=="konsultacje"){
            imageView.setImageResource(R.drawable.konsultacje)
        }
        else if(item?.kategoria=="sprzedaż"){
            imageView.setImageResource(R.drawable.sprzedaz)
        }
        else if(item?.kategoria=="sport"){
            imageView.setImageResource(R.drawable.sport)
        }
        else if(item?.kategoria=="zdrowie"){
            imageView.setImageResource(R.drawable.zdrowie)
        }
        else if(item?.kategoria=="serwis"){
            imageView.setImageResource(R.drawable.serwis)
        }
        else{
            imageView.setImageResource(R.drawable.inne)
        }

        reserve_btn.setOnClickListener{
            val intent = Intent(this, ReserveActivity::class.java)
            intent.putExtra("reserveCompany", item)
            startActivity(intent)
        }

        rate_btn.setOnClickListener {
            val intent = Intent(this, RateActivity::class.java)
            intent.putExtra("reserveCompany", item)
            startActivity(intent)
        }
    }
}
