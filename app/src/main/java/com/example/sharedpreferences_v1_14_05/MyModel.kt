package com.example.sharedpreferences_v1_14_05

import android.content.SharedPreferences

class MyModel ( private val sharedPreferences : SharedPreferences) {


    fun GuardarDatos(emailKey: String, email: String, contrasenakey: String, contrasena: String) {

        sharedPreferences.edit().apply {

            putString(emailKey, email)
            putString(contrasenakey, contrasena)
            apply()
        }

    }

    fun removeDatosFromPreferences(emailKey: String, contrasenakey: String) {

        sharedPreferences.edit().apply {
            remove(emailKey)
            remove(contrasenakey)
            apply()
        }

    }


    //Este método toma una clave como parámetro y devuelve el valor asociado con esa clave desde
    // las SharedPreferences. Si no se encuentra ningún valor asociado con la clave proporcionada,
    // devuelve null
    fun getEmailFromPreferences(key: String): String? {
        return sharedPreferences.getString(key, "")
    }


    // Esta función devuelve tanto el email como la contraseña guardados en SharedPreferences
    fun mostrar(emailKey: String, contrasenaKey: String): Pair<String?, String?> {
        val email = getEmailFromPreferences(emailKey)
        val contrasena = getEmailFromPreferences(contrasenaKey)
        return Pair(email, contrasena)
    }


    //*funcion para agregar un listado de correos y claves  NO ESTA IMPLEMENTADO

    fun agregarListadoDeCorreosYClaves(emails: List<String>, claves: List<String>) {
        // tamaño 0-9
        sharedPreferences.edit().apply {
            emails.forEachIndexed { index, correo ->
                putString("email_$index", correo)
            }

            claves.forEachIndexed { index, claves ->
                putString("contrasena_$index", claves)
            }
            apply()
        }
    }


    // funcion para obtener un listado de Elementos guardados en preferences NO ESTA IMPLEMENTADO

    fun getListadoDeCorreosYClaves(): Pair<List<String>, List<String>> {

        val emails = mutableListOf<String>()
        val claves = mutableListOf<String>()

        // *listado de datos de las preferencias  en un numero
        val keys = sharedPreferences.all.keys

        keys.forEach { key ->

            if (key.startsWith("email_")) {
                emails.add(sharedPreferences.getString(key, "") ?: "")
            } else if (key.startsWith("contrasena_")) {
                claves.add((sharedPreferences.getString(key, "") ?: ""))
            }

        }
        return Pair(emails, claves)
    }


}
