package com.example.pos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME3 ="MyDB3"
val TABLE_NAME3 ="Account"
val COL_ID3 ="ID"
val COL_USERNAME ="Username"
val COL_PASSWORD ="Password"

class AccountHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME3, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME3 +" (" + COL_ID3 +" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USERNAME + " VARCHAR(50), " + COL_PASSWORD + " VARCHAR(50))"
        db?.execSQL(createTable)
        db?.execSQL("INSERT INTO " + TABLE_NAME3 +"(Username, Password) VALUES('admin','admin')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3)
        onCreate(db)
    }
    fun addUser(accountClass: AccountClass){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERNAME,accountClass.username)
        cv.put(COL_PASSWORD,accountClass.password)
        var result = db.insert(TABLE_NAME3,null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun changePassword(id:String, accountClass: AccountClass): Boolean{
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USERNAME, accountClass.username)
        cv.put(COL_PASSWORD, accountClass.password)
        db.update(TABLE_NAME3,cv,"ID = ?", arrayOf(id))
        return true
    }

    fun getParticularAccount(id: String): AccountClass{
        var accountClass = AccountClass()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME3 + " WHERE " + COL_ID + " = '" + id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
                if (cursor.count >0){
                    do {
                        accountClass.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                        accountClass.username = cursor.getString(cursor.getColumnIndex(COL_ITEMCODE))
                        accountClass.password = cursor.getString(cursor.getColumnIndex(COL_ITEMNAME))
                    }while (cursor.moveToNext())
                }
        }
        finally {
            cursor.close()
        }

        return accountClass
    }


}