package com.example.pos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class InventoryAdapter(context: Context, arrayOfInv: ArrayList<InventoryClass>) : BaseAdapter(){
    var arrayOfInv : ArrayList<InventoryClass>;
    private val mInflator: LayoutInflater

    init {
        this.arrayOfInv = arrayOfInv
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfInv[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfInv.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null){
            view = this.mInflator.inflate(R.layout.listviewrow2,parent,false)
            vh = ListRowHolder(view)
            view.tag = vh
        }
        else{
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.code.text ="Category: " + arrayOfInv[position].itemcode
        vh.name.text = arrayOfInv[position].itemname
        vh.cost.text ="Cost: P"+ arrayOfInv[position].cost.toString()
        vh.quantity.text ="Quantity: " + arrayOfInv[position].quantity.toString()
        vh.total.text = "Total: P" + arrayOfInv[position].total.toString()
        vh.stock.text = "Remaining Stock: " + arrayOfInv[position].stock.toString()

        return view

    }
    private class ListRowHolder(row: View){
        public val code:TextView
        public val name:TextView
        public val cost:TextView
        public val quantity:TextView
        public val total:TextView
        public val stock:TextView

        init {
            this.code = row?.findViewById(R.id.textView12) as TextView
            this.name = row?.findViewById(R.id.textView13) as TextView
            this.cost = row?.findViewById(R.id.textView14) as TextView
            this.quantity = row?.findViewById(R.id.textView15) as TextView
            this.total = row?.findViewById(R.id.textView16) as TextView
            this.stock = row?.findViewById(R.id.textView17) as TextView
        }
    }
}