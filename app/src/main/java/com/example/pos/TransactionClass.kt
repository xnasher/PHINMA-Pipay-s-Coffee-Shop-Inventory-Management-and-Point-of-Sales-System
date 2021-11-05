package com.example.pos

class TransactionClass {
    var id : Int = 0
    var code : String = ""
    var name : String = ""
    var price : Float = 0.0F
    var quantity : Float = 0.0F
    var total : Float = 0F

    constructor(code:String, name:String, price:Float, quantity:Float, total:Float){
        this.code = code
        this.name = name
        this.price = price
        this.quantity = quantity
        this.total = total
    }
    constructor(){

    }
}