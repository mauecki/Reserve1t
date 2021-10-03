package com.example.reservapp.fragments.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reservapp.R
import kotlinx.android.synthetic.main.rate_row.view.*

class RateAdapter (
    private val rates: ArrayList<Rate>,
    private val listener: OnItemClickListener
):
    RecyclerView.Adapter<RateAdapter.UserViewHolder>(){

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rate_row,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val curRate = rates[position]
        holder.itemView.apply {
            nick_rv.text = curRate.nick
            opinia_rv.text=curRate.opinia
        }
    }

    fun addRate(rate: Rate){
        rates.add(rate)
        notifyItemInserted(rates.size-1)
    }


    override fun getItemCount(): Int {
        return rates.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}