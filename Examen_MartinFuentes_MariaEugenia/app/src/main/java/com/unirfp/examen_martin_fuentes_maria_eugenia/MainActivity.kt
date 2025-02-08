package com.unirfp.examen_martin_fuentes_maria_eugenia

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unirfp.examen_martin_fuentes_maria_eugenia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Variable necesaria para llamar a los componentes por su id
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Se configura View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogIn.setOnClickListener {
            val user = binding.etUser
            val pw = binding.etPW

            if (user.text.isNotEmpty()) {
                if (pw.text.isNotEmpty()) {
                    // Creamos la navegación entre pantallas con el object intent desde esta ventana (this) a la LoginSuccess
                    val intent = Intent(this,LoginSuccess::class.java)

                    //Se añaden los parámetros que queremos pasar a la otra activity
                    intent.putExtra("user", user.text.toString())
                    intent.putExtra("password", pw.text.toString())

                    //Iniciamos la otra activity
                    startActivity(intent)
                } else {
                    pw.setHintTextColor(Color.RED)
                    pw.setHint("Por favor, rellene este campo.")
                }
            } else {
                user.setHintTextColor(Color.RED)
                user.setHint("Por favor, rellene este campo.")
            }
        }


    }
}