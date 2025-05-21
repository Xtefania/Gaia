package com.example.gaia.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gaia.databinding.ActivityMainaBinding
import com.example.gaia.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar el Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorFragment, ListaProductosFragment())
            .commit()
    }
}