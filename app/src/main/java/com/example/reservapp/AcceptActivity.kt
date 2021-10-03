package com.example.reservapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reservapp.data.Company
import com.example.reservapp.data.CompanyViewModel
import com.example.reservapp.data.Reservation
import com.example.reservapp.fragments.list.OrderAdapter
import kotlinx.android.synthetic.main.activity_accept.*


class AcceptActivity : AppCompatActivity(), OrderAdapter.OnItemClickListener{

    private lateinit var mCompanyViewModel: CompanyViewModel
    val adapter = OrderAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept)
       val item = intent.getParcelableExtra<Company>("toAccept")!!
        val id:Int = item!!.id

        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        val recyclerView = MarkRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        zgloszenia.setText("Zgłoszenia dla: ")
        nazwa_zgloszen.setText(item.nazwa)

        mCompanyViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mCompanyViewModel.readCompanyToAccept(id).observe( this, androidx.lifecycle.Observer { reservation ->
            adapter.setData(reservation)
        })
    }

    override fun onItemClick(position: Int) {
        val clickedItem = adapter.getData()[position]
        val builder = AlertDialog.Builder(this)
        builder.setNeutralButton("Anuluj"){_,_->}
        builder.setNegativeButton("Odrzuć"){_,_->
            val updatedReservation = Reservation(clickedItem.idReserv, clickedItem.nazwaFirmy, clickedItem.idFirmy, clickedItem.mailUser, clickedItem.data, clickedItem.godzina, clickedItem.dodatkowe, clickedItem.mailOwner, 2, clickedItem.miasto, clickedItem.ulica, clickedItem.telefon, clickedItem.kategoria)
            mCompanyViewModel.updateReservation(updatedReservation)
            Toast.makeText(this, "Odrzucono", Toast.LENGTH_SHORT).show()
        }
        builder.setPositiveButton("Zaakceptuj"){_,_->
            val updatedReservation = Reservation(clickedItem.idReserv, clickedItem.nazwaFirmy, clickedItem.idFirmy, clickedItem.mailUser, clickedItem.data, clickedItem.godzina, clickedItem.dodatkowe, clickedItem.mailOwner, 1, clickedItem.miasto, clickedItem.ulica, clickedItem.telefon, clickedItem.kategoria)
            mCompanyViewModel.updateReservation(updatedReservation)
            Toast.makeText(this, "Zaakceptowano", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Zaakceptować zgłoszenie?")
        builder.setMessage("Zareaguj na zgłoszenie ${clickedItem.mailUser}")
        builder.create().show()    }
}