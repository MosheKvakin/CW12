package com.example.cw1

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cw1.ViewModel.MainViewModel
import com.example.cw1.data.Abonent
import kotlinx.android.synthetic.main.person_data_row.view.*

class ListAdapter(private val context: Context, private var mainViewModel: MainViewModel):RecyclerView.Adapter<ListAdapter.MyViewHolder>(){


    private var abonentlist = emptyList<Abonent>()


    fun setdata(items:List<Abonent>){
        this.abonentlist = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_data_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = abonentlist[position]
        holder.itemView.Id.text = item.Id.toString()
        holder.itemView.dfName.text = item.dfName
        holder.itemView.dfInn.text = item.dfInn
        holder.itemView.dfFlagLive.text = item.dfFlagLive.toString()

        holder.itemView.update.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.check.setOnClickListener {
            //val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            val action = ListFragmentDirections.actionListFragmentToCheckFragment(item)
            holder.itemView.findNavController().navigate(action)
        }


        holder.itemView.deletebtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){ _,_ ->

                mainViewModel.deleteAbonent(item)

                Toast.makeText(context,"Successfully removed: ${item.dfName}"
                    , Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No"){_,_ -> }
            builder.setTitle("Delete ${item.dfName}?")
            builder.setMessage("Are you sure , you want to delete ${item.dfName}?")
            builder.create().show()
        }
    }



    override fun getItemCount(): Int {
        return abonentlist.size
    }








}