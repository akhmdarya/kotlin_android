package com.example.app1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.example.app1.databinding.Navpage1Binding
import com.example.app1.fragments.firstFragment
import com.example.app1.fragments.secondFragment
import kotlinx.android.synthetic.main.navpage1.*

class NavPage1 : AppCompatActivity() {
    lateinit var binding: Navpage1Binding
    private val dataModel:DataTransfer by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navpage1)
        binding= Navpage1Binding.inflate(layoutInflater)
        setContentView(binding.root)


//        dataModel.message.observe(this,{
//            binding.recText.text=it
//
//
//        })
        val firstFragment= firstFragment()
        val secondFragment= secondFragment()
        makeCurrentFragment(firstFragment)
        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.menu_home->{makeCurrentFragment(firstFragment)


                }
                R.id.menu_set->makeCurrentFragment(secondFragment)

            }
            true
        }
    }


    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

}