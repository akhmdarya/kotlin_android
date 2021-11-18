package com.example.app1


import android.app.AlertDialog
import android.app.Dialog
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
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.afterlogin.*
import kotlinx.android.synthetic.main.modal_update.*


class New : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    lateinit var preferences: SharedPreferences

    lateinit var toogle:ActionBarDrawerToggle

    var textPref:TextView?=null
    var buttonLogOut:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afterlogin)
//        setSupportActionBar(toolbar)

        dbHelper= DBHelper(this)
        setupListofDataIntoRecyclerView()

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
                    R.id.buttonLogOut -> { val editor:SharedPreferences.Editor = preferences.edit()
                        editor.clear()
                        editor.apply()

                        val intent = Intent(this, MainActivity2::class.java)
//
                        startActivity(intent)
                        finish()}

                }
            true
        }


        textPref=findViewById(R.id.textPref)
        buttonLogOut=findViewById(R.id.buttonLogOut)


        preferences=getSharedPreferences("SHARED_PREF",Context.MODE_PRIVATE)
        val mail=preferences.getString("MAIL","").toString()
        textPref?.text="$mail,  Hello!"
        Log.d("MAILLL?????????", textPref?.text.toString())

        buttonLogOut?.setOnClickListener(){


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




    private fun getItemsList(): ArrayList<EmpModelClass> {
        //creating the instance of DatabaseHandler class

        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = dbHelper.viewUsers()
        Log.d("USERRRRSSSSS", empList.toString())
        return empList
    }
    private fun setupListofDataIntoRecyclerView() {

        if (getItemsList().size > 0) {

            rvItemsListt?.visibility = View.VISIBLE
            // tvNoRecordsAvailable.visibility = View.GONE

            // Set the LayoutManager that this RecyclerView will use.
            rvItemsListt?.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = ItemAdapter(this, getItemsList())
            // adapter instance is set to the recyclerview to inflate the items.
            rvItemsListt?.adapter = itemAdapter
        } else {

            rvItemsListt?.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }

    fun updateRecordDialog(empModelClass: EmpModelClass) {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.modal_update)

        updateDialog.etUpdateName.setText(empModelClass.name)
        updateDialog.etUpdateEmailId.setText(empModelClass.mail)

        updateDialog.tvUpdate.setOnClickListener(View.OnClickListener {

            val name = updateDialog.etUpdateName.text.toString()
            val mail = updateDialog.etUpdateEmailId.text.toString()

//            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if (!name.isEmpty() && !mail.isEmpty()) {
                val status =
                    dbHelper.updateUser(EmpModelClass(empModelClass.id,name, mail))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                    setupListofDataIntoRecyclerView()

                    updateDialog.dismiss() // Dialog will be dismissed
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Name or Email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        updateDialog.tvCancel.setOnClickListener(View.OnClickListener {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    fun deleteRecordAlertDialog(empModelClass: EmpModelClass) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Record")
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete ${empModelClass.name}.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class

            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status = dbHelper.deleteUser(EmpModelClass(empModelClass.id, "", ""))
            if (status > -1) {
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_LONG
                ).show()

                setupListofDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }



}