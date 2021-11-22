package com.example.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.app1.fragments.firstFragment
import com.example.app1.fragments.secondFragment
import kotlinx.android.synthetic.main.navpage1.*

class NavPage1 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navpage1)
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