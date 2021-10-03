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
import kotlinx.android.synthetic.main.accept_row.view.*
import kotlinx.android.synthetic.main.reservation_row.view.*

class OrderAdapter(
    private val listener: OnItemClickListener
):
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

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
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.accept_row, parent, false))
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

    override fun onBindViewHolder(holder: OrderAdapter.MyViewHolder, position: Int) {
        val currentItem = reservationList[position]
        holder.itemView.godzina_rvv.text = currentItem.godzina
        holder.itemView.termin_rvv.text = currentItem.data
        holder.itemView.firma_rvv.text = currentItem.mailUser
        holder.itemView.szczegoly_rvv.text = currentItem.dodatkowe

        if(currentItem.check==0){
            holder.itemView.check_rvv.setImageResource(R.drawable.ic_baseline_help_outline_24)
        }
        else if(currentItem.check==1){
            holder.itemView.check_rvv.setImageResource(R.drawable.ic_baseline_done_24)
        }
        else{
            holder.itemView.check_rvv.setImageResource(R.drawable.ic_baseline_close_24)
        }
    }
}