package com.example.reservapp
import com.example.reservapp.R



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.reservapp.data.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_events.*

private lateinit var mCompanyViewModel: CompanyViewModel

var adapter= listOf<Company>()
class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        val item = intent.getParcelableExtra<Reservation>("event")
        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        nazwaFirmy_ev.setText("Nazwa firmy: ${item?.nazwaFirmy}")
        kategoria_ev.setText("kategoria: ${item?.kategoria}")
        idFirmy_ev.setText("id firmy: ${item?.idFirmy}")
        telefon_ev.setText("numer telefonu: ${item?.telefon}")
        mail_ev.setText("adres email: ${item?.mailOwner}")
        miasto_ev.setText("miasto: ${item?.miasto}")
        ulica_ev.setText("ulica, nr budynku: ${item?.ulica}")
        termin_ev.setText("Termin zarezerwowanej usługi: ${item?.data}")
        opis_ev.setText("Opis: ${item?.dodatkowe}")
        godzina_evv.setText("godzina: ${item?.godzina}")

        if(item?.check==1){
            imageView3.setImageResource(R.drawable.ic_baseline_done_24)
            zaakceptowane_ev.setText("Zaakceptowane przez przedsiębiorstwo")
        }
        else if(item?.check==0){
            imageView3.setImageResource(R.drawable.ic_baseline_help_outline_24)
            zaakceptowane_ev.setText("Oczekiwane na akcpetacje")
        }
        else{
            imageView3.setImageResource(R.drawable.ic_baseline_close_24)
            zaakceptowane_ev.setText("Odrzucone przez przedsiębiorstwo")
        }

    floatingActionButton.setOnClickListener {

            deleteReservation(item!!)

    }

    }

    private fun deleteReservation(item :Reservation) {
        val builder = AlertDialog.Builder(this)
        builder.setNegativeButton("Nie"){_,_->}
        builder.setPositiveButton("Tak"){_,_->
            mCompanyViewModel.deleteReservation(item.idReserv)
            Toast.makeText(this, "Usunięto ${item.nazwaFirmy} z rezerwacji", Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setTitle("Usunąć ${item.nazwaFirmy}?")
        builder.setMessage("Na pewno chcesz usunąć ${item.nazwaFirmy} z rezerwacji? ")
        builder.create().show()
    }

}