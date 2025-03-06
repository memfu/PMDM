package com.unirfp.examen_2aev_martinfuentes_mariaeugenia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model.Producto

class ProductAdapter (val productos: List<Producto>) : RecyclerView.Adapter<ProductoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return ProductoViewHolder(layoutInflater.inflate(R.layout.itemproducto,parent,false))
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item : Producto = productos[position]
        holder.bind(item)
    }
}