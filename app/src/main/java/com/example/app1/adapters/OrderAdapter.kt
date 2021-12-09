package com.example.app1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.listitem.view.*

import com.example.app1.R
import android.util.Log
import com.example.app1.fragments.sertificateDetails
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.datamodels.ModelOrder


class OrderAdapter(val context: Context, val items: ArrayList<ModelOrder>)
    : Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.listitem,
                parent,
                false
            )
        )
    }
   // @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)

        holder.sertificateDetail.text = item.sertificateDetail
        holder.sertificateName.text = item.sertificateName
        holder.sertificatePhoto.setImageDrawable(holder.view.context.getDrawable(item.sertificatePhoto))

        holder.view.setOnClickListener(object:View.OnClickListener{



            override fun onClick(v: View?) {
              //  Log.d("))))))))))))))))))))))0", holder.sertificateName.text.toString())
                val activity=v?.context as AppCompatActivity
                val sertificateDetails=sertificateDetails()
                activity.supportFragmentManager.beginTransaction().replace( R.id.fl_wrapper,sertificateDetails).addToBackStack(null)
                    .commit()

            }

        })

   }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {
        // Holds the TextView that will add each item to


        val sertificateDetail = view.description
        val sertificateName = view.sertificateName
        val sertificatePhoto = view.sertificatePhoto


        }

    }

