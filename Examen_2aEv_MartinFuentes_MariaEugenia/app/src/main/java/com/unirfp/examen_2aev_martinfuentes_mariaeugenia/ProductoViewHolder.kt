package com.unirfp.examen_2aev_martinfuentes_mariaeugenia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.databinding.ItemproductoBinding
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model.Producto

class ProductoViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemproductoBinding.bind(view)

    fun bind (producto: Producto) {
        Picasso.get().load(producto.image).into(binding.ivProducto)
        binding.tvNombre.setText("Artículo: " + producto.name)
        binding.tvDescripcion.setText(producto.description)
        binding.tvPrecio.setText("Precio: " + producto.price.toString())
    }

}