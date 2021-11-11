package com.example.app1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.internal.NavigationMenuView
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.find


class New : AppCompatActivity() {
    lateinit var preferences: SharedPreferences

    lateinit var toogle:ActionBarDrawerToggle

    var textPref:TextView?=null
    var buttonLogOut:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        val navigationView : NavigationView =findViewById(R.id.navigation)
        toogle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {

                when(it.itemId){
                    R.id.nav_home -> Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                    R.id.nav_set -> Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                    R.id.nav_log -> Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()

                }
            true
        }


        textPref=findViewById(R.id.textPref)
        buttonLogOut=findViewById(R.id.buttonLogOut)


        preferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        val mail=preferences.getString("MAIL"," ").toString()
        textPref?.text="$mail,  Hello!"
        Log.d("MAILLL?????????", textPref?.text.toString())

        buttonLogOut?.setOnClickListener(){
            val editor:SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity2::class.java)
//
            startActivity(intent)
            finish()

        }



//        var usermail=""
//        usermail = intent.extras?.getString("usermail").toString()
//
//        textPref?.text="$usermail, Hello!"



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}