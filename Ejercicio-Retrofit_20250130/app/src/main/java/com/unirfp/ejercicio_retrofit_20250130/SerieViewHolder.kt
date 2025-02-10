package com.unirfp.ejercicio_retrofit_20250130

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unirfp.ejercicio_retrofit_20250130.databinding.ItemserieBinding
import com.unirfp.ejercicio_retrofit_20250130.model.Serie

class SerieViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemserieBinding.bind(view)

    fun bind(serie: Serie) {
        Picasso.get().load(serie.image).into(binding.ivSerie)
        binding.tvTitle.setText("Título: "+ serie.title)
        binding.tvCreator.setText("Creador: "+ serie.creator)
        binding.tvRating.setText("Puntuación: "+ serie.rating.toString())
    }

}