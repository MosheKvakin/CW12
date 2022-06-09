package com.example.cw1

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodSubtype
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val ims: InputMethodSubtype =
            getSystemService<Any>(Context.INPUT_METHOD_SERVICE).getCurrentInputMethodSubtype()
        val locale = ims.locale
        */
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}