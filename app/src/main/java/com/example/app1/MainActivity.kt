package com.example.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private   var user_field : EditText?=null
    private var button: Button?=null
    private var result_info: TextView?=null
    private  var textView:TextView?=null
    private var newView=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        user_field=findViewById(R.id.user_field)
        button=findViewById(R.id.button)
        result_info=findViewById(R.id.result_info)
        textView=findViewById(R.id.textView)

        button?.setOnClickListener{

            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            val countString=textView?.text.toString()
            val count = Integer.parseInt(countString)

//            intent.putExtra("total",count.toString())

            startActivity(intent)
            Toast.makeText(this,"!!!!!!!!!!",Toast.LENGTH_LONG).show()

//            if (user_field?.text?.toString()?.trim()?.equals("")!!){
//                Toast.makeText(this,"Введите url",Toast.LENGTH_LONG).show()
//            }
//
//
//            else{
//                var url_user:String = user_field!!.text.toString()
//
//                var url: String="https://flask-gdpr.herokuapp.com/parse/$url_user"
//
//                doAsync{
//                    val response= URL(url).readText()
//                    Log.d("INFO",response)
//                  val data=  JSONObject(response).getString("Privacy_link")
//                    result_info?.text="Privacy_link : $data"
//                }
//
//            }

        }


}
    fun count(view:View){
        val countString=textView?.text.toString()
        var count: Int= Integer.parseInt(countString)
        count++
        textView?.text= count.toString()
    }

}