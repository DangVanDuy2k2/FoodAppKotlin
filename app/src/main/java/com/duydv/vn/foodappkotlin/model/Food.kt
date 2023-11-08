package com.duydv.vn.foodappkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Food")
data class Food(
    @PrimaryKey
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val price: Int = 0,
    val popular: Boolean = false,
    val sale: Int = 0,
    val description: String = ""
) : java.io.Serializable {

    fun getRealPrice(): Int {
        return if (sale <= 0) {
            price
        } else {
            price - (price * sale) / 100
        }
    }
}