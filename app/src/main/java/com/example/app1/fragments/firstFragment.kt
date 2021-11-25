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
import com.example.app1.DataTransfer
import com.example.app1.R
import com.example.app1.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_second.*

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

        binding?.butToFr?.setOnClickListener{
            dataModel.messagefragment2.value="Hello fr 2!"
        }
        binding?.butToMain?.setOnClickListener{
            dataModel.message.value="Hello from fr 1"
        }


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