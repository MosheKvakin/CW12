package com.example.cw1

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.text.set
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.cw1.Api.*
import com.example.cw1.ViewModel.MainViewModel
import com.example.cw1.data.Abonent
import io.ktor.client.request.*
import kotlinx.android.synthetic.main.fragment_check.*
import kotlinx.android.synthetic.main.fragment_check.view.*
import kotlinx.android.synthetic.main.fragment_check_tin.*
import kotlinx.android.synthetic.main.fragment_check_tin.view.*
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName


class CheckFragmentTIN : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_check_tin, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        view.checkeddfName.setText(args.currentAbonent.dfName)
        view.checkeddfInn.setText(args.currentAbonent.dfInn)


        view.checktinbutton.setOnClickListener {
            checkAbonentData()
        }


        return view
    }


    private fun checkAbonentData() {

        val  checkeddfInn = checkeddfInn.text.toString()





        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch  {
            println("Корутина выполняется на потоке: ${Thread.currentThread().name}")

            //val innId = "2310171088" //dfInn
            //checkeddfInn

            val itemsEgr = Api().getItemEgr(checkeddfInn)
            val tin: String
            if (itemsEgr.items.size > 0) {
                tin = "КПП:" + itemsEgr.items[0].ulItem.kpp.toString()
            }
            else
            {
                tin = "Информация об КПП не найдена. Рекомендуем проверить правильность введённых данных и повторить попытку поиска."
            }
            withContext(Dispatchers.Main) {
                view?.checktinResult?.setText(tin)
            }

        }



    ///    if(inputCheck(dfName,dfInn))

            //val updateabonent = Abonent(args.currentAbonent.Id,dfName,dfInn,dfFlagLiveData)
            //mainViewModel.updateAbonent(updateabonent)

            Toast.makeText(requireContext(),"Checked successfully", Toast.LENGTH_SHORT).show()

            //findNavController().navigate(R.id.action_checkFragment_to_listFragment)



    }


    private fun inputCheck(
        dfName: String ,dfInn : String
       ):Boolean{
        return !(TextUtils.isEmpty(dfName) && TextUtils.isEmpty(dfInn) /*&& dfFlagLiveData.isEmpty()*/)
    }
}