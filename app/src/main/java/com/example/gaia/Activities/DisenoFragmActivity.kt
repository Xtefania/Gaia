package com.example.gaia.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.gaia.Fragments.CarritoFragment
import com.example.gaia.Fragments.CategoriasFragment
import com.example.gaia.Fragments.MapFragment
import com.example.gaia.Fragments.MiCuentaFragment
import com.example.gaia.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DisenoFragmActivity : AppCompatActivity() {

    private lateinit var navigation: BottomNavigationView

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.fragm_categorias -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, ListaProductosFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }

            R.id.fragm_micuenta -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, MiCuentaFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }

            R.id.fragm_map -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, MapFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }

            R.id.fragm_carrito -> {
                supportFragmentManager.commit {
                    replace(R.id.frameContainer, CarritoFragment())
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                true
            }

            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diseno_fragm)

        navigation = findViewById(R.id.navMenu)
        navigation.setOnNavigationItemSelectedListener(mOnNavMenu)

        // Mostrar ListaProductosFragment al iniciar
        supportFragmentManager.commit {
            replace(R.id.frameContainer, ListaProductosFragment())
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}
