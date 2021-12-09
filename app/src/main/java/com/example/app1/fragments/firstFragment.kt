package com.example.app1.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.*
import com.example.app1.adapters.OrderAdapter
import com.example.app1.databinding.FragmentFirstBinding
import com.example.app1.datamodels.ModelOrder
import com.example.app1.db.DBHelper
import kotlinx.android.synthetic.main.fragment_first.*

//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [firstFragment.newInstance] factory method to
// * create an instance of this fragment.
// */


class firstFragment : Fragment() {

    private val dataModel:DataTransfer by activityViewModels()
    var binding: FragmentFirstBinding? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirstBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListofDataIntoRecyclerView()



//        binding?.butToFr?.setOnClickListener{
//            dataModel.messagefragment2.value="Hello fr 2!"
//        }
//        binding?.butToMain?.setOnClickListener{
//            dataModel.message.value="Hello from fr 1"
//        }


    }

    companion object {

       @JvmStatic
      fun newInstance() =firstFragment()
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }


    private fun getItemsList(): ArrayList<ModelOrder> {
         lateinit var dbHelper: DBHelper
        //creating the instance of DatabaseHandler class

        //calling the viewEmployee method of DatabaseHandler class to read the records
        //val empList: ArrayList<ModelOrder> = dbHelper.viewUsers()
        val empList: ArrayList<ModelOrder> =ArrayList<ModelOrder>(3)
        empList.add(ModelOrder("Sertif1",getString(R.string.place_of_study),R.drawable.appeal_applic))
        empList.add(ModelOrder("Sertif2",getString(R.string.military_registr),R.drawable.military_registr))
        Log.d("SERtIfICATES", empList.toString())
        return empList
    }

    private fun setupListofDataIntoRecyclerView() {

        if (getItemsList().size > 0) {
             Log.d("ordeeers", getItemsList().toString())

            recyclerView?.visibility = View.VISIBLE
//            // tvNoRecordsAvailable.visibility = View.GONE
//
//            // Set the LayoutManager that this RecyclerView will use.
            recyclerView?.layoutManager = LinearLayoutManager(activity)
//            // Adapter class is initialized and list is passed in the param.
           val itemAdapter = activity?.let { getItemsList()?.let { it1 -> OrderAdapter(it, it1) } }
            // adapter instance is set to the recyclerview to inflate the items.
            recyclerView?.adapter = itemAdapter
    }
    }
}