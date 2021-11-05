package com.example.pos

class InventoryClass {
    var id : Int = 0
    var itemcode : String = ""
    var itemname : String = ""
    var cost : Float = 0.0F
    var quantity : Float = 0.0F
    var total : Float = 0F
    var stock : Float = 0F

    constructor(itemcode:String, itemname:String, cost:Float, quantity:Float, total:Float, stock:Float){
        this.itemcode = itemcode
        this.itemname = itemname
        this.cost = cost
        this.quantity = quantity
        this.total = total
        this.stock = stock
    }
    constructor(){

    }
}