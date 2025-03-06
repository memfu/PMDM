package com.unirfp.examen_2aev_martinfuentes_mariaeugenia

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.databinding.ActivityMainBinding
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model.Producto
import com.unirfp.examen_2aev_martinfuentes_mariaeugenia.model.ProductoResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter
    private val productoList = mutableListOf<Producto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        fetchProductos("products/?page=1")
    }

    private fun initRecyclerView() {
        adapter = ProductAdapter(productoList)
        binding.rvProductos.layoutManager = LinearLayoutManager(this)
        binding.rvProductos.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun fetchProductos(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ProductoResponse> = getRetrofit()
                .create(ApiService::class.java)
                .getAllProducts(query)

            val response:ProductoResponse? = call.body()

            runOnUiThread{
                if (call.isSuccessful) {
                    val productosL = response?.results?: emptyList()
                    productoList.clear()
                    productoList.addAll(productosL)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show()
    }
}