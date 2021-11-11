package com.example.app1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import org.jetbrains.anko.find

class MainActivity2 : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    var textViewNum:TextView?=null
    var name:EditText?=null
    var mail:EditText?=null
    var saveButton:Button?=null
    var buttonRegister:Button?=null
   var buttonLogin:Button?=null
    var password:EditText?=null
    var login_password:EditText?=null
    var login_mail:EditText?=null
   var registration_layout: LinearLayout?=null
    var layout_new: LinearLayout?=null
    var checkBox:CheckBox?=null


    lateinit var sharedPreferences: SharedPreferences
    var isremembered  = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newxml)

        dbHelper= DBHelper(this)

        sharedPreferences = getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        isremembered= sharedPreferences.getBoolean("CHECKBOX",false)
        if (isremembered){
            val intent = Intent(this@MainActivity2, New::class.java)
//                intent.putExtra("usermail",usermail.toString())
            startActivity(intent)
            Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
        }
    registration_layout=findViewById(R.id.registration_layout)
        layout_new=findViewById(R.id.layout_new)

        textViewNum=findViewById(R.id.textViewNum)
        saveButton=findViewById(R.id.saveButton)
        buttonRegister=findViewById(R.id.buttonRegister)
        buttonLogin=findViewById(R.id.buttonLogin)
        password=findViewById(R.id.password)
       name=findViewById(R.id.name)
        mail=findViewById(R.id.mail)
       login_mail=findViewById(R.id.loginmail)
       login_password=findViewById(R.id.loginpassword)
        checkBox=findViewById(R.id.checkBox)

//        Log.d("INFO?????????", mail?.text.toString() )
        var total=""
        total = intent.extras?.getString("total").toString()

        textViewNum?.text=total.toString()


        buttonRegister?.setOnClickListener(){
            showRegistration()

        }

        saveButton?.setOnClickListener(){

            dbHelper.insertUserData(name?.text.toString(),mail?.text.toString(), password?.text.toString())
            showLogin()


        }
        buttonLogin?.setOnClickListener( ){

            if (dbHelper.userIsInDB(login_mail?.text.toString(),login_password?.text.toString())){
                Toast.makeText(this, "KEK", Toast.LENGTH_SHORT).show()
            }
            else{

                val usermail=login_mail?.text.toString()
                    Log.d("INFO?????????", usermail )

                val checked: Boolean? =checkBox?.isChecked
                val editor :SharedPreferences.Editor=sharedPreferences.edit()
                editor.putString("MAIL",usermail)
                editor.putBoolean("CHECKBOX", checked!!)
                editor.apply()

                val intent = Intent(this@MainActivity2, New::class.java)
               intent.putExtra("usermail",usermail.toString())
                startActivity(intent)
                Toast.makeText(this, "LOL", Toast.LENGTH_SHORT).show()
         }

        }
    }

    fun showRegistration(){
        layout_new?.visibility=View.INVISIBLE
        registration_layout?.visibility= View.VISIBLE


    }
    fun showLogin(){
        layout_new?.visibility=View.VISIBLE
        registration_layout?.visibility= View.INVISIBLE


    }




}