package com.example.app1.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.DataTransfer
import com.example.app1.R
import com.example.app1.adapters.CartAdapter
import com.example.app1.adapters.OrderAdapter
import com.example.app1.databinding.FragmentFirstBinding
import com.example.app1.databinding.FragmentSecondBinding
import com.example.app1.datamodels.ModelOrder
import com.example.app1.db.DBHelper
import com.example.app1.db.OrderContract
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
private  const val REQUEST_CODE=42
class secondFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    lateinit var mAdapter:CartAdapter
    private val packageManager: PackageManager? =null
    private val dataModel: DataTransfer by activityViewModels()
    var binding: FragmentSecondBinding? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSecondBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val LOADER=0
        loaderManager.initLoader(LOADER,null,this)

        var listView:ListView= binding!!.list

        //val empList: ArrayList<ModelOrder> =ArrayList<ModelOrder>(0)
         mAdapter = CartAdapter(activity!!,null)
        listView.setAdapter(mAdapter)


       // setupListofDataIntoRecyclerView()



    }

//    private fun getItemsList(): ArrayList<ModelOrder> {
//        lateinit var dbHelper: DBHelper
//
//
//        //creating the instance of DatabaseHandler class
//
//        //calling the viewEmployee method of DatabaseHandler class to read the records
//        //val empList: ArrayList<ModelOrder> = dbHelper.viewUsers()
//
//
////        val empList: ArrayList<ModelOrder> =ArrayList<ModelOrder>(0)
////        var mAdapter = CartAdapter(activity!!,null)
////        empList.setAda
////        empList.add(ModelOrder("Sertif1",getString(R.string.place_of_study),R.drawable.appeal_applic))
////        empList.add(ModelOrder("Sertif2",getString(R.string.military_registr),R.drawable.military_registr))
////        Log.d("SERtIfICATES", empList.toString())
////        return empList
//    }

//    private fun setupListofDataIntoRecyclerView() {
//        if (getItemsList().size > 0) {
//            Log.d("ordeeers", getItemsList().toString())
//
//            recyclerView?.visibility = View.VISIBLE
////            // tvNoRecordsAvailable.visibility = View.GONE
////
////            // Set the LayoutManager that this RecyclerView will use.
//            recyclerView?.layoutManager = LinearLayoutManager(activity)
////            // Adapter class is initialized and list is passed in the param.
//            val itemAdapter = activity?.let { getItemsList()?.let { it1 -> OrderAdapter(it, it1) } }
//            // adapter instance is set to the recyclerview to inflate the items.
//            recyclerView?.adapter = itemAdapter
//        }
//    }

    companion object {

        @JvmStatic
        fun newInstance() =firstFragment()
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        var projection :Array<String> = arrayOf(
            OrderContract.OrderEntry._ID, OrderContract.OrderEntry.COLUMN_NAME,
            OrderContract.OrderEntry.COLUMN_PRICE,
            OrderContract.OrderEntry.COLUMN_QUANTITY,
            OrderContract.OrderEntry.COLUMN_EXTRA_SERT

        )
        lateinit var mCurrentCartUri: Uri;
        return CursorLoader(activity!!,OrderContract.OrderEntry.CONTENT_URI,
            projection,
            null,
            null,
            null)

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        mAdapter.swapCursor(data)

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
mAdapter.swapCursor(null)
    }
}