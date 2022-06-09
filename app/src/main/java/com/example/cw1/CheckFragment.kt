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
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName


class CheckFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_check, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //view.checkdfName.setText(args.currentAbonent.dfName)
        //view.checkdfInn.setText(args.currentAbonent.dfInn)


        view.checkbutton.setOnClickListener {
            checkAbonentData()
        }


        return view
    }


    private fun checkAbonentData() {

        val nam = checkNam.text.toString()
        val fam = checkFam.text.toString()
        val otch = checkOtch.text.toString()
        val bdate = checkBdate.text.toString()
        val doctype = checkDoctype.text.toString()
        val docno = checkDocno.text.toString()




        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch  {
            println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
           /* val innId = "2310171088" //dfInn

            println("innId:"+ innId)
            val itemsEgr = Api().getItemEgr(innId)
            println("res1:${itemsEgr.items[0].ulItem[0].kpp}")
            view?.checkdfName?.setText("numtel1:"+itemsEgr.items[0].ulItem[0].kpp)
            */

           /* val passportExpire: PassportExpire = Api().getPassport("0317741452")
            println("res:"+ passportExpire.result)
          val tinPerson : TINPerson = Api().getTINPerson()
              println("res:"+ tinPerson.result)

            */

            //val inn: FSS = Api().getFSS(innId)
            /*poster.load("${BuildConfig.API_IMAGE_BASE_URL}${movie.posterPath}") {
                transformations(RoundedCornersTransformation(16f))
            }

             */

            var tin:String;
            val tinPersonItems : TINPersonItems = Api().getTINPerson2(fam,nam,otch,bdate,doctype,docno)
            if (tinPersonItems.results.size > 0) {
                //println("res1:${tinPersonItems.results}")
                tin = "ИНН:" + tinPersonItems.results[0].result.toString()
            }
            else
            {
                tin = "Информация об ИНН не найдена. Рекомендуем проверить правильность введённых данных и повторить попытку поиска."
            }
            withContext(Dispatchers.Main) {
                view?.checkResult?.setText(tin)
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