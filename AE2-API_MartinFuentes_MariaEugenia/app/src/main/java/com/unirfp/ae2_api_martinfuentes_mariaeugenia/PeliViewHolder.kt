package com.unirfp.ae2_api_martinfuentes_mariaeugenia

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.databinding.ItemPeliBinding
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.Peli

class PeliViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var binding = ItemPeliBinding.bind(view) // este es el layout aparte que luego se coloca en el RecyclerView

    fun bind(peli: Peli) {
        Picasso.get().load(peli.poster).into(binding.ivPeli)
        binding.tvTitle.setText("Título: " + peli.titulo)
        binding.tvActors.setText("Elenco: " + peli.actores)
        binding.tvPlot.setText("Sinopsis: " + peli.plot)
        binding.tvRating.setText("Puntuación IMBD: " + peli.imdbRating)
    }
}