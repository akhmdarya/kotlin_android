package com.example.app1.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.app1.DataTransfer
import com.example.app1.R
import com.example.app1.databinding.FragmentFirstBinding
import com.example.app1.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_second.*
private  const val REQUEST_CODE=42
class secondFragment : Fragment() {
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
        dataModel.messagefragment2.observe(activity as LifecycleOwner,{
          binding?.getText?.text=it
        })

//        btn_open.setOnClickListener{
//            val takePictureIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            if(takePictureIntent.resolveActivity(this.packageManager!!)!=null) {
//                startActivityForResult(takePictureIntent, REQUEST_CODE)
//            }
//            else {
//                Toast.makeText(this.context, "Can not open cam", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//            if(requestCode == REQUEST_CODE && resultCode== Activity.RESULT_OK){
//                val takenImage=data?.extras?.get("data") as Bitmap
//                image_view.setImageBitmap(takenImage)
//
//            }
//            else {
//                super.onActivityResult(requestCode, resultCode, data)
//            }
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
}