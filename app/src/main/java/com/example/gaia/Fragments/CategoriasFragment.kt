package com.example.gaia.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.gaia.Activities.ListProductActivity
import com.example.gaia.Activities.LoginActivity
import com.example.gaia.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_categorias, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCerrarSesion = view.findViewById<Button>(R.id.btn_cerrarsesion)

        btnCerrarSesion.setOnClickListener {
            cerrarSesion(requireContext())
        }

        super.onViewCreated(view, savedInstanceState)

        val buttonIds = listOf(
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView9,
            R.id.textView10,
            R.id.textView11,
            R.id.textView8,
        )

        for (id in buttonIds) {
            val textView = view.findViewById<TextView>(id)
            textView.setOnClickListener {
                parentFragmentManager.commit {
                    replace(R.id.frameContainer, InfoProductFragment())
                    setReorderingAllowed(true)
                    addToBackStack("fromCategorias")
                }
            }
        }

    }

    private fun cerrarSesion(context: Context) {
        val prefs = context.getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
        prefs.edit().remove("usuario_actual").apply()


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)

        googleSignInClient.signOut().addOnCompleteListener {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoriasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = CategoriasFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}