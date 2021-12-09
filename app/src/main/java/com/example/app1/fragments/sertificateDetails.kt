package com.example.app1.fragments

import android.app.Activity
import android.content.ClipData
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri

import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get


import androidx.fragment.app.activityViewModels
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.*
import com.example.app1.databinding.FragmentFirstBinding
import com.example.app1.databinding.FragmentSertificateDetailsBinding
import com.example.app1.db.OrderContract
import kotlinx.android.synthetic.main.afterlogin.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_sertificate_details.*
import kotlinx.android.synthetic.main.navpage1.*





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


class sertificateDetails : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private val dataModel:DataTransfer by activityViewModels()
    var binding: FragmentSertificateDetailsBinding? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSertificateDetailsBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var imageView =binding?.imageViewInfo
        var plusquantity =binding?.addquantity
        var minus =binding?.subquantity
        var quantityNumber =binding?.quantityInfo
        var sertName =binding?.sertNameinInfo
        var sertPrice =binding?.sertPrice
        var addSert =binding?.addSert
        var  mCurrentCartUri: Uri;


        sertName?.setText("Сertificate from the place of study")

        plusquantity?.setOnClickListener(object:View.OnClickListener {

            override fun onClick(v: View?) {
                var basePrice=3
                val countString = binding?.quantityInfo?.text.toString()
                var count: Int = Integer.parseInt(countString)
                count++
                binding?.quantityInfo?.text = count.toString()

                var orderPrice=basePrice * count


                var ifcheckBox=checktotal(basePrice,count)
                orderPrice=ifcheckBox


                sertPrice?.text=orderPrice.toString()



            }
        })
        minus?.setOnClickListener(object:View.OnClickListener {

            override fun onClick(v: View?) {
                var basePrice=3
                val countString = binding?.quantityInfo?.text.toString()
                var count: Int = Integer.parseInt(countString)
                if (count==0)
                {
                    Toast.makeText(context,"Can t decrease",Toast.LENGTH_SHORT).show()
                    var orderPrice=basePrice * count
                    var ifcheckBox=checktotal(basePrice,count)
                    orderPrice=ifcheckBox
                    sertPrice?.text=orderPrice.toString()
                }
                else {
                    count--
                    var orderPrice=basePrice * count
                    var ifcheckBox=checktotal(basePrice,count)
                    orderPrice=ifcheckBox
                    sertPrice?.text=orderPrice.toString()
                }


                binding?.quantityInfo?.text = count.toString()

            }
        })


        addtocart.setOnClickListener(object:View.OnClickListener {

            override fun onClick(v: View?) {
                saveCart()
                val activity=v?.context as AppCompatActivity
                val secondFragment=secondFragment()
                activity.supportFragmentManager.beginTransaction().replace( R.id.fl_wrapper,secondFragment).addToBackStack(null)
                    .commit()
//save values to bd


               //R.id.menu_set.state_checked="true"

                //setBackgroundColor(0x00FF00)
            }
        })





    }

    fun saveCart():Boolean {
        var imageView =binding?.imageViewInfo
        var plusquantity =binding?.addquantity
        var minus =binding?.subquantity
        var quantityNumber =binding?.quantityInfo
        var sertName =binding?.sertNameinInfo
        var sertPrice =binding?.sertPrice
        var addSert =binding?.addSert
        var mCurrentCartUri: Uri? =null;
        var hasAllRequiredValues = false


        var name= sertName?.text.toString()
        var price= sertPrice?.text.toString()
        var quantity= quantityNumber?.text.toString()
        Log.d("%%%%%%%%%%%", name )
        val values: ContentValues = ContentValues()

        values.put(OrderContract.OrderEntry.COLUMN_NAME,name)
        values.put(OrderContract.OrderEntry.COLUMN_PRICE,price)
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY,quantity)
        if (addSert?.isChecked == true){
            values.put(OrderContract.OrderEntry.COLUMN_EXTRA_SERT,"HAS EXTRA SERT: YES")
        }
        else{
            values.put(OrderContract.OrderEntry.COLUMN_EXTRA_SERT,"HAS EXTRA SERT: NO")
        }

        if (mCurrentCartUri == null) {
            val newUri: Uri? = context?.getContentResolver()?.insert(OrderContract.OrderEntry.CONTENT_URI, values)
            if (newUri == null) {
                Toast.makeText(activity, "Failed to add to Cart", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Success  adding to Cart", Toast.LENGTH_SHORT).show()
            }
        }
        hasAllRequiredValues=true
        return hasAllRequiredValues
    }


    fun checktotal( basePrice:Int,count:Int ):Int{

            var res=0
                if (addSert?.isChecked == true) {

                  res=basePrice+2
                    Log.d( "HHHHHHHHHH",res.toString())
                }
        else{
            res=basePrice
        }



        return res*count
    }

    companion object {

        @JvmStatic
        fun newInstance() =sertificateDetails()
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
      return CursorLoader(activity!!,mCurrentCartUri,
          projection,
          null,
          null,
          null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, cursor: Cursor?) {
     if (cursor ==null || cursor.count<1){
         return

         }
        if(cursor.moveToFirst()){
            var name= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME)
            var price= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE)
            var quantity= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY)
            var addsert= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_EXTRA_SERT)


            var nameofsert=cursor.getString(name)
            var priceofsert=cursor.getString(price)
            var quantityofsert=cursor.getString(quantity)
            var addsertofsert=cursor.getString(addsert)

            sertNameinInfo.setText(nameofsert)
            sertPrice.setText(priceofsert)
            quantityInfo.setText(quantityofsert)


        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        sertNameinInfo.setText("")
        sertPrice.setText("")
        quantityInfo.setText("")
    }


}



