package com.example.cw1

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cw1.ViewModel.MainViewModel
import com.example.cw1.data.Abonent
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view =  inflater.inflate(R.layout.fragment_add, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        view.addbutton.setOnClickListener {
            addAbonentDataToDatabase()
        }

        return view
    }

    private fun addAbonentDataToDatabase() {

        val dfName = adddfName.text.toString()
        val dfInn = adddfInn.text.toString()
        val dfFlagLive = adddfFlagLive.text

        if(inputCheck(dfName,dfInn,dfFlagLive)){

            val abonent = Abonent(0,dfName,dfInn,Integer.parseInt(dfFlagLive.toString()))
            mainViewModel.addAbonent(abonent)

            Toast.makeText(requireContext(),"Successfully Added",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

    }

    private fun inputCheck(dfName: String, dfInn: String, dfFlagLive: Editable):Boolean{
        // Здесь проверка на валидность ИНН
        return !(TextUtils.isEmpty(dfName) && TextUtils.isEmpty(dfInn) /*&& dfFlagLive.isEmpty()*/)
    }
}