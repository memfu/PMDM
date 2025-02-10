package com.unirfp.ae2_api_martinfuentes_mariaeugenia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.Peli

class PeliAdapter(private val pelis: List<Peli>) : RecyclerView.Adapter<PeliViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliViewHolder(layoutInflater.inflate(R.layout.item_peli,parent,false))
    }

    override fun getItemCount(): Int {
        return this.pelis.size
    }

    override fun onBindViewHolder(holder: PeliViewHolder, position: Int) {
       val item = this.pelis[position]
        holder.bind(item)
    }

}