package com.example.gaia.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.gaia.Fragments.CategoriasFragment
import com.example.gaia.R
import androidx.fragment.app.commit
import com.example.gaia.Fragments.CarritoFragment
import com.example.gaia.Fragments.MapFragment
import com.example.gaia.Fragments.MiCuentaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class DisenoFragmActivity: AppCompatActivity() {

    lateinit var navegation : BottomNavigationView

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item   ->

        when (item.itemId){
            R.id.fragm_categorias -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, CategoriasFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.fragm_micuenta -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, MiCuentaFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.fragm_map -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, MapFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.fragm_carrito -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, CarritoFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }


            }
            false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diseno_fragm)


        navegation = findViewById(R.id.navMenu)
        navegation.setOnNavigationItemSelectedListener (mOnNavMenu)

        supportFragmentManager.commit {
            replace(R.id.frameContainer, CategoriasFragment())
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }



    }
}