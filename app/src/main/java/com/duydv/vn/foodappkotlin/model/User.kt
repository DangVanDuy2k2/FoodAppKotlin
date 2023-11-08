package com.duydv.vn.foodappkotlin.model

data class User(var email: String, var password: String, var isAdmin: Boolean) {
    constructor(email: String, password: String) : this(email, password, false)
}