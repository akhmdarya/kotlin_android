package com.example.app1.adapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.app1.R
import com.example.app1.datamodels.ModelOrder
import com.example.app1.db.OrderContract
import kotlinx.android.synthetic.main.fragment_sertificate_details.*
import kotlinx.android.synthetic.main.listitem.view.*

class CartAdapter( context: Context, c: Cursor?):
    CursorAdapter(context, c, 0)
 {


    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.cartlist,parent,false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        var sertNameSum: TextView? = view?.findViewById(R.id.sertNameinOrderSummary)
        var priceSum: TextView? = view?.findViewById(R.id.priceinOrderSummary)
        var addSertSum: TextView? = view?.findViewById(R.id.addSert)
        var quantitySum: TextView? = view?.findViewById(R.id.quantityinOrderSummary)

        var name= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME)
        var price= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE)
        var quantity= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY)
        var addsert= cursor?.getColumnIndex(OrderContract.OrderEntry.COLUMN_EXTRA_SERT)


        var nameofsert=cursor?.getString(name!!)
        var priceofsert=cursor?.getString(price!!)
        var quantityofsert=cursor?.getString(quantity!!)
        var addsertofsert=cursor?.getString(addsert!!)


        sertNameSum?.setText(nameofsert)
        priceSum?.setText(priceofsert)
        addSertSum?.setText(quantityofsert)
        quantitySum?.setText(addsertofsert)

    }
}