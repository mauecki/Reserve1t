package com.example.reservapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.custom_row.view.*

class UpdateActivity : AppCompatActivity() {

    private lateinit var mCompanyViewModel: CompanyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val item = intent.getParcelableExtra<Company>("firma")

        val clicked_id = item?.id!!.toInt()
        val clicked_mail = item.mail
        val clicked_category = item.kategoria
        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        nazwa_up.setText(item?.nazwa)
        wlasciciel_up.setText(item?.wlascicel)
        telefon_up.setText(item?.telefon)
        miasto_up.setText(item?.miasto)
        ulica_up.setText(item?.ulica)
        opis_up.setText(item?.opis)

        if(item?.kategoria=="korepetycje"){
            imageView9.setImageResource(R.drawable.korepetycje)
        }
        else if(item?.kategoria=="mechanika"){
            imageView9.setImageResource(R.drawable.mechanika)
        }
        else if(item?.kategoria=="fryzjerstwo"){
            imageView9.setImageResource(R.drawable.fryzjerstwo)
        }
        else if(item?.kategoria=="kosmetyka"){
            imageView9.setImageResource(R.drawable.kosmetyka)
        }
        else if(item?.kategoria=="konsultacje"){
            imageView9.setImageResource(R.drawable.konsultacje)
        }
        else if(item?.kategoria=="sprzedaż"){
            imageView9.setImageResource(R.drawable.sprzedaz)
        }
        else if(item?.kategoria=="sport"){
            imageView9.setImageResource(R.drawable.sport)
        }
        else if(item?.kategoria=="zdrowie"){
            imageView9.setImageResource(R.drawable.zdrowie)
        }
        else if(item?.kategoria=="serwis"){
            imageView9.setImageResource(R.drawable.serwis)
        }
        else{
            imageView9.setImageResource(R.drawable.inne)
        }

        upd_btn.setOnClickListener{
            updateItem(clicked_id, clicked_mail, clicked_category)
        }

        item_del.setOnClickListener{
            deleteCompany(item)
        }

        see_det.setOnClickListener {
            val intent = Intent(this, AcceptActivity::class.java)
            intent.putExtra("toAccept", item)
            startActivity(intent)
        }

        rate_profile.setOnClickListener {
            val intent = Intent(this, MarkActivity::class.java)
            intent.putExtra("toAccept", item)
            startActivity(intent)
        }
    }

    private fun deleteCompany(item :Company) {
        val builder = AlertDialog.Builder(this)
        builder.setNegativeButton("Nie"){_,_->}
        builder.setPositiveButton("Tak"){_,_->
            mCompanyViewModel.deleteWithId(item.id)
            mCompanyViewModel.deleteCompany(item)
            Toast.makeText(this, "Usunięto ${item.nazwa}", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setTitle("Usunąć ${item.nazwa}?")
        builder.setMessage("Na pewno chcesz usunąć ${item.nazwa}? ")
        builder.create().show()
    }

    private fun updateItem(clicked_id: Int, clicked_mail: String, clicked_category: String){
        val nazwa = nazwa_up.text.toString()
        val wlasciciel = wlasciciel_up.text.toString()
        val telefon = telefon_up.text.toString()
        val miasto = miasto_up.text.toString()
        val ulica = ulica_up.text.toString()
        val opis = opis_up.text.toString()

        if(inputCheck(nazwa, wlasciciel, telefon, miasto, ulica, clicked_category, opis)){
            val updatedCompany = Company(clicked_id, nazwa, wlasciciel, telefon, miasto, ulica, clicked_category, opis,clicked_mail)
            mCompanyViewModel.updateCompany(updatedCompany)
            Toast.makeText(this, "Zaktualizowano dane firmy", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Proszę wypełnić wszystkie dane", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nazwa:String, wlasciciel: String, telefon: String, miasto: String,ulica: String, kategoria: String, opis: String): Boolean{
        return !(TextUtils.isEmpty(nazwa) && TextUtils.isEmpty(wlasciciel) && TextUtils.isEmpty(telefon) && TextUtils.isEmpty(miasto) && TextUtils.isEmpty(ulica)&& TextUtils.isEmpty(kategoria)&& TextUtils.isEmpty(opis) )
    }
}