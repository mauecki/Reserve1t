package com.example.reservapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reservapp.R
import com.example.reservapp.data.Company
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.reservation_row.view.*

class ListAdapter(
    private val listener: OnItemClickListener
): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position= adapterPosition
            if(position != RecyclerView.NO_POSITION){
            listener.onItemClick(position)
            }
        }
    }

    private var companyList = emptyList<Company>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem = companyList[position]

        holder.itemView.id_rv.text = currentItem.id.toString()
        holder.itemView.nazwa_rv.text = currentItem.nazwa.toString()
        holder.itemView.wlasciciel_rv.text = currentItem.wlascicel.toString()
        holder.itemView.miasto_rv.text = currentItem.miasto.toString()
        holder.itemView.kategoria_rv.text = currentItem.kategoria.toString()

        if(currentItem.kategoria=="korepetycje"){
            holder.itemView.logo_rv.setImageResource(R.drawable.korepetycje)
        }
        else if(currentItem.kategoria=="mechanika"){
            holder.itemView.logo_rv.setImageResource(R.drawable.mechanika)
        }
        else if(currentItem.kategoria=="fryzjerstwo"){
            holder.itemView.logo_rv.setImageResource(R.drawable.fryzjerstwo)
        }
        else if(currentItem.kategoria=="kosmetyka"){
            holder.itemView.logo_rv.setImageResource(R.drawable.kosmetyka)
        }
        else if(currentItem.kategoria=="konsultacje"){
            holder.itemView.logo_rv.setImageResource(R.drawable.konsultacje)
        }
        else if(currentItem.kategoria=="sprzedaż"){
            holder.itemView.logo_rv.setImageResource(R.drawable.sprzedaz)
        }
        else if(currentItem.kategoria=="sport"){
            holder.itemView.logo_rv.setImageResource(R.drawable.sport)
        }
        else if(currentItem.kategoria=="zdrowie"){
            holder.itemView.logo_rv.setImageResource(R.drawable.zdrowie)
        }
        else if(currentItem.kategoria=="serwis"){
            holder.itemView.logo_rv.setImageResource(R.drawable.serwis)
        }
        else{
            holder.itemView.logo_rv.setImageResource(R.drawable.inne)
        }
    }

    override fun getItemCount(): Int {
    return companyList.size
    }

    fun setData(company :List<Company>){
        this.companyList = company
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun getData(): List<Company> {
        return companyList
    }

}