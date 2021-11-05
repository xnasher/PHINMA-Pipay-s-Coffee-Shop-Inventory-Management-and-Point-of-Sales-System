package com.example.pos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME2 ="MyDB2"
val TABLE_NAME2 ="Tran"
val COL_ID2 ="ID"
val COL_PRODUCTCODE ="ProductCode"
val COL_PRODUCTNAME ="ProductName"
val COL_PRICE ="Price"
val COL_QUANTITY2 ="Quantity"
val COL_TOTAL2 ="Total"

class TransactionHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME2, null, 1) {
    override fun onCreate(db2: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME2 + " (" + COL_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PRODUCTCODE + " VARCHAR(20), " + COL_PRODUCTNAME + " VARCHAR(100), " + COL_PRICE + " FLOAT, " + COL_QUANTITY2 + " FLOAT, " + COL_TOTAL2 + " FLOAT)"
        db2?.execSQL(createTable)
    }

    override fun onUpgrade(db2: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db2?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2)
        onCreate(db2)
    }

    fun insertTransaction(transactionClass: TransactionClass) {
        val db2 = this.writableDatabase
        var cv2 = ContentValues()
        cv2.put(COL_PRODUCTCODE, transactionClass.code)
        cv2.put(COL_PRODUCTNAME, transactionClass.name)
        cv2.put(COL_PRICE, transactionClass.price)
        cv2.put(COL_QUANTITY2, transactionClass.quantity)
        cv2.put(COL_TOTAL2, transactionClass.total)
        var result = db2.insert(TABLE_NAME2, null, cv2)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }
    }

    fun updateTransaction(id: String, transactionClass: TransactionClass): Boolean {
        val db2 = this.writableDatabase
        var cv2 = ContentValues()
        cv2.put(COL_PRODUCTCODE, transactionClass.code)
        cv2.put(COL_PRODUCTNAME, transactionClass.name)
        cv2.put(COL_PRICE, transactionClass.price)
        cv2.put(COL_QUANTITY2, transactionClass.quantity)
        cv2.put(COL_TOTAL2, transactionClass.total)
        db2.update(TABLE_NAME2, cv2, "ID = ?", arrayOf(id))
        return true
    }

    fun deleteTransaction(id: String): Int {
        val db2 = this.writableDatabase
        return db2.delete(TABLE_NAME2, "ID = ?", arrayOf(id))
    }

    fun listOfTransaction() : ArrayList<TransactionClass>{
        var list  = ArrayList<TransactionClass>()
        val db2 = this.readableDatabase
        val query2 = "Select * from " + TABLE_NAME2
        val result2 = db2.rawQuery(query2, null)
        if (result2.moveToFirst()){
            do {
                var transactionClass = TransactionClass()
                transactionClass.id = result2.getString(result2.getColumnIndex(COL_ID2)).toInt()
                transactionClass.code = result2.getString(result2.getColumnIndex(COL_PRODUCTCODE))
                transactionClass.name = result2.getString(result2.getColumnIndex(COL_PRODUCTNAME))
                transactionClass.price = result2.getString(result2.getColumnIndex(COL_PRICE)).toFloat()
                transactionClass.quantity = result2.getString(result2.getColumnIndex(COL_QUANTITY2)).toFloat()
                transactionClass.total = result2.getString(result2.getColumnIndex(COL_TOTAL2)).toFloat()
                list.add(transactionClass)
            }while (result2.moveToNext())
        }
        result2.close()
        db2.close()
        return list
    }

    fun getAllTransaction() : ArrayList<TransactionClass>{
        val tranList: ArrayList<TransactionClass> = arrayListOf<TransactionClass>()
        val cursor2: Cursor = readableDatabase.query(TABLE_NAME2, arrayOf(COL_ID2, COL_PRODUCTCODE, COL_PRODUCTNAME, COL_PRICE, COL_QUANTITY2, COL_TOTAL2), null, null, null, null, null, null,)
        try {
            if (cursor2.count !=0){
                cursor2.moveToFirst()
                if (cursor2.count >0){
                    do {
                        val id : Int = cursor2.getInt(cursor2.getColumnIndex(COL_ID2))
                        val code : String = cursor2.getString(cursor2.getColumnIndex(COL_PRODUCTCODE))
                        val name : String = cursor2.getString(cursor2.getColumnIndex(COL_PRODUCTNAME))
                        val price : Float = cursor2.getFloat(cursor2.getColumnIndex(COL_PRICE))
                        val quantity : Float = cursor2.getFloat(cursor2.getColumnIndex(COL_QUANTITY2))
                        val total : Float = cursor2.getFloat(cursor2.getColumnIndex(COL_TOTAL2))
                        var transactionClass = TransactionClass()
                        transactionClass.id = id
                        transactionClass.code = code
                        transactionClass.name = name
                        transactionClass.price = price
                        transactionClass.quantity = quantity
                        transactionClass.total = total
                        tranList.add(transactionClass)
                    }while (cursor2.moveToNext())
                }
            }
        }
        finally {
            cursor2.close()
        }
        return tranList
    }

    fun getParticularTransaction(id: String): TransactionClass{
        var transactionClass = TransactionClass()
        val db2 = this.readableDatabase
        val selectQuery2 = "SELECT  * FROM " + TABLE_NAME2 + " WHERE " + COL_ID2 + " = '" + id + "'"
        val cursor2 = db2.rawQuery(selectQuery2, null)
        try {
            if (cursor2.count !=0){
                cursor2.moveToFirst()
                if (cursor2.count >0){
                    do {
                        transactionClass.id = cursor2.getInt(cursor2.getColumnIndex(COL_ID2))
                        transactionClass.code = cursor2.getString(cursor2.getColumnIndex(COL_PRODUCTCODE))
                        transactionClass.name = cursor2.getString(cursor2.getColumnIndex(COL_PRODUCTNAME))
                        transactionClass.price = cursor2.getFloat(cursor2.getColumnIndex(COL_PRICE))
                        transactionClass.quantity = cursor2.getFloat(cursor2.getColumnIndex(COL_QUANTITY2))
                        transactionClass.total = cursor2.getFloat(cursor2.getColumnIndex(COL_TOTAL2))
                    }while (cursor2.moveToNext())
                }
            }
        }
        finally {
            cursor2.close()
        }

        return transactionClass
    }
    fun getTotalTransaction():Float{
        val db2 = this.writableDatabase
        val cursor = db2.rawQuery("SELECT SUM(" + COL_TOTAL2 +") AS TotalTransaction FROM " + TABLE_NAME2,null)
        var result :Float = 0F
        if (cursor.moveToFirst()){
            result = cursor.getFloat(cursor.getColumnIndex("TotalTransaction"))
        }
        return result
    }
}