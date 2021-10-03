package com.example.reservapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.reservapp.R
import com.example.reservapp.data.Reservation
import kotlinx.android.synthetic.main.reservation_row.view.*

class ReservationAdapter(
    private val listener: OnItemClickListener
):
    RecyclerView.Adapter<ReservationAdapter.MyViewHolder>() {

    private var reservationList = emptyList<Reservation>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
            listener.onItemClick(position)
            }
        }

        init{
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reservation_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = reservationList[position]
        holder.itemView.godzina_rv.text = currentItem.godzina
        holder.itemView.termin_rv.text = currentItem.data
        holder.itemView.firma_rv.text = currentItem.nazwaFirmy
        holder.itemView.szczegoly_rv.text = currentItem.dodatkowe

        if(currentItem.check==0){
            holder.itemView.check_rv.setImageResource(R.drawable.ic_baseline_help_outline_24)
        }
        else if(currentItem.check==1){
            holder.itemView.check_rv.setImageResource(R.drawable.ic_baseline_done_24)
        }
        else{
            holder.itemView.check_rv.setImageResource(R.drawable.ic_baseline_close_24)
        }

    }

    override fun getItemCount(): Int {
        return reservationList.size
    }

    fun setData(reservation: List<Reservation>){
        this.reservationList = reservation
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun getData(): List<Reservation> {
        return reservationList
    }
}