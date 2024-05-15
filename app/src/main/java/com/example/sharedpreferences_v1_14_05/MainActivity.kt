package com.example.sharedpreferences_v1_14_05

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.sharedpreferences_v1_14_05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)


        binding.apply {


            agregar.setOnClickListener{

                val email= editTextTextEmailAddress.text.toString()
                val  contrasena= editTextTextPassword.text.toString()
                val keyEmail ="emailkey"
                val keyContrasena="contrasenaKey"


                // guardar datos
                viewModel.guardarDatos(keyEmail,email,keyContrasena,contrasena)
                // LIMPIO CAMPOS DESPUES DE GUARDAR
                binding.editTextTextEmailAddress.setText("")
                 binding.editTextTextPassword.setText("")



                // mostrar datos

                viewModel.mostrarDatos(keyEmail,keyContrasena)
            }


            eliminar.setOnClickListener{

                val keyEmail ="emailKey"
                val keyContrasena="contrasenaKey"

                // eliminar datos
                viewModel.eliminarDatos(keyEmail,keyContrasena)

                //limpiar el textiview Depues

                textView2.text=""
            }
        }

        // Observar cambios en el LiveData y actualizar el TextView
        viewModel.emailLiveData.observe(this) { email ->
            binding.textView2.text = "Email: $email"
        }

        viewModel.contresenaKey.observe(this) { contrasena ->
            binding.textView2.append("\nContraseña: $contrasena")
        }
    }



    }
