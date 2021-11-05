package com.example.pos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TransactionAdapter(context: Context, arrayOfTran: ArrayList<TransactionClass>): BaseAdapter() {
    var arrayOfTran : ArrayList<TransactionClass>;
    private val mInflator: LayoutInflater

    init {
        this.arrayOfTran = arrayOfTran
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getItem(position: Int): Any {
        return arrayOfTran[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayOfTran.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView==null){
            view=this.mInflator.inflate(R.layout.listviewrow,parent,false)
            vh = ListRowHolder(view)
            view.tag = vh
        }
        else{
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.code.text =" Product Code: " + arrayOfTran[position].code
        vh.name.text =arrayOfTran[position].name
        vh.price.text ="Price: P"+arrayOfTran[position].price.toString()
        vh.quantity.text ="Quantity: " + arrayOfTran[position].quantity.toString()
        vh.total.text="Total: P" + arrayOfTran[position].total.toString()
        return view
    }
    private class ListRowHolder(row: View){
        public val code: TextView
        public val name: TextView
        public val price: TextView
        public val quantity: TextView
        public val total: TextView

        init {
            this.code = row?.findViewById(R.id.textView12) as TextView
            this.name = row?.findViewById(R.id.textView13) as TextView
            this.price = row?.findViewById(R.id.textView14) as TextView
            this.quantity = row?.findViewById(R.id.textView15) as TextView
            this.total = row?.findViewById(R.id.textView16) as TextView
        }
    }
}