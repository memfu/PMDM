package com.unirfp.examen_martin_fuentes_maria_eugenia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unirfp.examen_martin_fuentes_maria_eugenia.databinding.ActivityLoginSuccessBinding

class LoginSuccess : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Se recoge el textview
        val resultTV = binding.tvDatosLogin

        // Ahora necesitamos los datos de la pantalla anterior
        val user: String = intent.extras?.getString("user").orEmpty()
        val password: String = intent.extras?.getString("password").orEmpty()

        //Modificamos el contenido del resultTV
        resultTV.text = "Sus datos: \n Usuario: $user \n Contraseña: $password"
    }
}