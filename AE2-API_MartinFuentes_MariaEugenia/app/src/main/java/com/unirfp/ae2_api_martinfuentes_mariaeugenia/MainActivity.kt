package com.unirfp.ae2_api_martinfuentes_mariaeugenia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.databinding.ActivityMainBinding
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.Peli
import com.unirfp.ae2_api_martinfuentes_mariaeugenia.model.PeliResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    // TODO: conseguir una APIKEY de https://www.omdbapi.com/
    private val apiKey = ""

    private lateinit var peliAdapter: PeliAdapter
    private var peliList = mutableListOf<Peli>() // necesitamos una lista preparada que pueda cambiar de valores

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

           this.initRecyclerView()
           this.initResetButton()

           binding.svTitulo.setOnQueryTextListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initRecyclerView() {
        peliAdapter = PeliAdapter(peliList)
        binding.rvPelis.layoutManager = LinearLayoutManager(this)
        binding.rvPelis.adapter = peliAdapter
    }


    private fun initResetButton() {
        binding.btnReset.setOnClickListener {
            peliList.clear()
            peliAdapter.notifyDataSetChanged()
            binding.svTitulo.setQuery("", false)
            Toast.makeText(this, "Búsqueda reseteada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create()) // para serializar los ficheros
            .build()
    }

    // Función con llamada asíncrona con corrutina
    private fun searchMoviesByTitle(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<PeliResponse> = getRetrofit()
                .create(ApiService::class.java)
                .getMoviesBySearch(apiKey, query) // Busca películas con coincidencia parcial

            if (call.isSuccessful) {
                val peliResponse: PeliResponse? = call.body()
                val uniqueMovies = mutableSetOf<String>() // Set para evitar duplicados
                val newPeliList = mutableListOf<Peli>() // Lista temporal para evitar conflictos con la UI

                peliResponse?.busqueda?.forEach { movie ->
                    val movieDetailsCall: Response<Peli> = getRetrofit()
                        .create(ApiService::class.java)
                        .getMovieByTitle(apiKey, movie.titulo)

                    if (movieDetailsCall.isSuccessful) {
                        val fullMovie: Peli? = movieDetailsCall.body()
                        fullMovie?.let {
                            if (!uniqueMovies.contains(it.imdbID)) { // Verifica que no esté repetido
                                newPeliList.add(it)
                                uniqueMovies.add(it.imdbID)
                            }
                        }
                    }
                }

                runOnUiThread {
                    if (newPeliList.isNotEmpty()) {
                        peliList.clear()
                        peliList.addAll(
                            newPeliList.distinctBy { it.imdbID } // Elimina duplicados
                            .sortedByDescending { it.imdbRating.toDoubleOrNull() ?: 0.0 } // Ordena por IMDb Rating
                        )
                        peliAdapter.notifyDataSetChanged()
                    } else {
                        showError("No se encontraron películas con: $query")
                    }
                }
            } else {
                showError("Error en la búsqueda")
            }
        }
    }



    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()) {
            this.searchMoviesByTitle(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true //para que no coja los datos mientras el usuario escribe
    }
}