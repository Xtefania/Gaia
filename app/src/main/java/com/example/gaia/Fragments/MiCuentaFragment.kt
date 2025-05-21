package com.example.gaia.Fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gaia.Activities.LoginActivity
import com.example.gaia.R
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject

class MiCuentaFragment : Fragment() {

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_mi_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireContext().getSharedPreferences("UsuariosApp", Context.MODE_PRIVATE)
        val emailActual = prefs.getString("usuario_actual", null)

        if (emailActual == null) {
            Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            return
        }

        val usuarioStr = prefs.getString(emailActual, null)
        if (usuarioStr == null) {
            Toast.makeText(context, "Datos no encontrados", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = JSONObject(usuarioStr)

        // Referencias
        val etNombre = view.findViewById<TextInputEditText>(R.id.editTextNombre)
        val etApellido = view.findViewById<TextInputEditText>(R.id.editTextLastName)
        val etEmail = view.findViewById<TextInputEditText>(R.id.editTextEmail)
        val etTelefono = view.findViewById<TextInputEditText>(R.id.editTextTelefono)
        val etFecha = view.findViewById<TextInputEditText>(R.id.editTextFechaNacimiento)
        val etGenero = view.findViewById<TextInputEditText>(R.id.editTextGender)
        val ivFoto = view.findViewById<ImageView>(R.id.iv_AgregarFoto)

        // Mostrar datos
        etNombre.setText(usuario.getString("nombre"))
        etApellido.setText(usuario.getString("apellido"))
        etEmail.setText(usuario.getString("email"))
        etTelefono.setText(usuario.getString("telefono"))
        etFecha.setText(usuario.getString("fechaNacimiento"))
        etGenero.setText(usuario.getString("genero"))

        val uriFoto = usuario.optString("fotoPerfilUri", "")
        if (uriFoto.isNotEmpty()) {
            try {
                ivFoto.setImageURI(Uri.parse(uriFoto))
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(context, "No se puede acceder a la imagen", Toast.LENGTH_SHORT).show()
            }
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                101
            )
        }

        ivFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Botón actualizar
        view.findViewById<Button>(R.id.btn_guardar).setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val nuevoApellido = etApellido.text.toString()
            val nuevoEmail = etEmail.text.toString()
            val nuevoTelefono = etTelefono.text.toString()
            val nuevaFecha = etFecha.text.toString()
            val nuevoGenero = etGenero.text.toString()
            val nuevaFotoUri = selectedImageUri?.toString() ?: usuario.optString("fotoPerfilUri", "")

            val contrasenaActual = usuario.optString("contrasena", "")

            usuario.put("nombre", nuevoNombre)
            usuario.put("apellido", nuevoApellido)
            usuario.put("email", nuevoEmail)
            usuario.put("telefono", nuevoTelefono)
            usuario.put("fechaNacimiento", nuevaFecha)
            usuario.put("genero", nuevoGenero)
            usuario.put("fotoPerfilUri", nuevaFotoUri)
            usuario.put("contrasena", contrasenaActual)

            val editor = prefs.edit()
            editor.remove(emailActual)
            editor.putString(nuevoEmail, usuario.toString())
            editor.putString("usuario_actual", nuevoEmail)
            editor.apply()

            Toast.makeText(context, "Datos actualizados", Toast.LENGTH_SHORT).show()
        }

        // Botón eliminar
        view.findViewById<Button>(R.id.btn_eliminar).setOnClickListener {
            prefs.edit().remove(emailActual).remove("usuario_actual").apply()
            Toast.makeText(context, "Cuenta eliminada", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            view?.findViewById<ImageView>(R.id.iv_AgregarFoto)?.setImageURI(selectedImageUri)
        }
    }
}




// PARA ELIMINAR
/*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gaia.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*/
/**
 * A simple [Fragment] subclass.
 * Use the [MiCuentaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *//*

class MiCuentaFragment : Fragment() {
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_mi_cuenta, container, false)
    }

    companion object {
        */
/**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PerfilFragment.
         *//*

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MiCuentaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}*/
