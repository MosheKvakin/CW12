package com.example.cw1

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cw1.ViewModel.MainViewModel
import com.example.cw1.data.Abonent
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        view.updatedfName.setText(args.currentAbonent.dfName)
        view.updatedfInn.setText(args.currentAbonent.dfInn)
        view.updatedfFlagLive.setText(args.currentAbonent.dfFlagLive.toString())

        view.updatebutton.setOnClickListener {
            updatepersondata()
        }


        return view
    }


    private fun checkAbonent() {

        val dfName = updatedfName.text.toString()
        val dfInn = updatedfInn.text.toString()
        val dfFlagLiveData = Integer.parseInt(updatedfFlagLive.text.toString())

        if(inputCheck(dfName,dfInn,dfFlagLiveData)){
            val updateabonent = Abonent(args.currentAbonent.Id,dfName,dfInn,dfFlagLiveData)
            mainViewModel.updateAbonent(updateabonent)

            Toast.makeText(requireContext(),"Checked Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun updatepersondata() {

        val dfName = updatedfName.text.toString()
        val dfInn = updatedfInn.text.toString()
        val dfFlagLiveData = Integer.parseInt(updatedfFlagLive.text.toString())

        if(inputCheck(dfName,dfInn,dfFlagLiveData)){
            val updateabonent = Abonent(args.currentAbonent.Id,dfName,dfInn,dfFlagLiveData)
            mainViewModel.updateAbonent(updateabonent)

            Toast.makeText(requireContext(),"Updated Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun inputCheck(
        dfName: String ,dfInn : String,dfFlagLiveData : Int
       ):Boolean{
        return !(TextUtils.isEmpty(dfName) && TextUtils.isEmpty(dfInn) /*&& dfFlagLiveData.isEmpty()*/)
    }
}