package com.example.pos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val TABLE_NAME ="Inventory"
val COL_ID ="ID"
val COL_ITEMCODE ="ItemCode"
val COL_ITEMNAME ="ItemName"
val COL_COST ="Cost"
val COL_QUANTITY ="Quantity"
val COL_TOTAL ="Total"
val COL_STOCK ="Stock"

class InventoryHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 3){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME +" (" + COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ITEMCODE + " VARCHAR(20), " + COL_ITEMNAME + " VARCHAR(100), " + COL_COST + " FLOAT, " + COL_QUANTITY + " FLOAT, " + COL_TOTAL + " FLOAT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
        if(newVersion>oldVersion){
            db?.execSQL("ALTER TABLE " + TABLE_NAME +" ADD COLUMN "+ COL_STOCK +" FLOAT")
        }
    }
    fun insertData(inventoryClass: InventoryClass){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ITEMCODE, inventoryClass.itemcode)
        cv.put(COL_ITEMNAME, inventoryClass.itemname)
        cv.put(COL_COST, inventoryClass.cost)
        cv.put(COL_QUANTITY, inventoryClass.quantity)
        cv.put(COL_TOTAL, inventoryClass.total)
        cv.put(COL_STOCK, inventoryClass.stock)
        var result = db.insert(TABLE_NAME,null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }
    }
    fun updateData(id: String, inventoryClass: InventoryClass ): Boolean{
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ITEMCODE, inventoryClass.itemcode)
        cv.put(COL_ITEMNAME, inventoryClass.itemname)
        cv.put(COL_COST, inventoryClass.cost)
        cv.put(COL_QUANTITY, inventoryClass.quantity)
        cv.put(COL_TOTAL, inventoryClass.total)
        cv.put(COL_STOCK, inventoryClass.stock)
        db.update(TABLE_NAME, cv, "ID = ?", arrayOf(id))
        return true
    }
    fun deleteData(id: String) : Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
    }
    fun listOfInventory() : ArrayList<InventoryClass>{
        var list  = ArrayList<InventoryClass>()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                var inventoryClass = InventoryClass()
                inventoryClass.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                inventoryClass.itemcode = result.getString(result.getColumnIndex(COL_ITEMCODE))
                inventoryClass.itemname = result.getString(result.getColumnIndex(COL_ITEMNAME))
                inventoryClass.cost = result.getString(result.getColumnIndex(COL_COST)).toFloat()
                inventoryClass.quantity = result.getString(result.getColumnIndex(COL_QUANTITY)).toFloat()
                inventoryClass.total = result.getString(result.getColumnIndex(COL_TOTAL)).toFloat()
                inventoryClass.stock = result.getString(result.getColumnIndex(COL_STOCK)).toFloat()
                list.add(inventoryClass)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun getAllInventory() : ArrayList<InventoryClass>{
        val invList: ArrayList<InventoryClass> = arrayListOf<InventoryClass>()
        val cursor: Cursor = readableDatabase.query(TABLE_NAME, arrayOf(COL_ID, COL_ITEMCODE, COL_ITEMNAME, COL_COST, COL_QUANTITY, COL_TOTAL, COL_STOCK), null, null, null, null, null, null)
        try {
            if (cursor.count !=0){
                cursor.moveToFirst()
                if (cursor.count >0){
                    do {
                        val id : Int = cursor.getInt(cursor.getColumnIndex(COL_ID))
                        val code : String = cursor.getString(cursor.getColumnIndex(COL_ITEMCODE))
                        val name : String = cursor.getString(cursor.getColumnIndex(COL_ITEMNAME))
                        val cost : Float = cursor.getFloat(cursor.getColumnIndex(COL_COST))
                        val quantity : Float = cursor.getFloat(cursor.getColumnIndex(COL_QUANTITY))
                        val total : Float = cursor.getFloat(cursor.getColumnIndex(COL_TOTAL))
                        val stock : Float = cursor.getFloat(cursor.getColumnIndex(COL_STOCK))
                        var inventoryClass = InventoryClass()
                        inventoryClass.id = id
                        inventoryClass.itemcode = code
                        inventoryClass.itemname = name
                        inventoryClass.cost = cost
                        inventoryClass.quantity = quantity
                        inventoryClass.total = total
                        inventoryClass.stock = stock
                        invList.add(inventoryClass)
                    }while (cursor.moveToNext())
                }
            }
        }
        finally {
            cursor.close()
        }
        return invList
    }
    fun getParticularInventory(id: String): InventoryClass{
        var inventoryClass = InventoryClass()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.count !=0){
                cursor.moveToFirst()
                if (cursor.count >0){
                    do {
                        inventoryClass.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                        inventoryClass.itemcode = cursor.getString(cursor.getColumnIndex(COL_ITEMCODE))
                        inventoryClass.itemname = cursor.getString(cursor.getColumnIndex(COL_ITEMNAME))
                        inventoryClass.cost = cursor.getFloat(cursor.getColumnIndex(COL_COST))
                        inventoryClass.quantity = cursor.getFloat(cursor.getColumnIndex(COL_QUANTITY))
                        inventoryClass.total = cursor.getFloat(cursor.getColumnIndex(COL_TOTAL))
                        inventoryClass.stock = cursor.getFloat(cursor.getColumnIndex(COL_STOCK))
                    }while (cursor.moveToNext())
                }
            }
        }
        finally {
            cursor.close()
        }

        return inventoryClass
    }
    fun getTotalInventory():Float{
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT SUM(" + COL_TOTAL +") AS TotalInventory FROM " + TABLE_NAME,null)
        var result :Float = 0F
        if (cursor.moveToFirst()){
            result = cursor.getFloat(cursor.getColumnIndex("TotalInventory"))
        }
        return result
    }
}