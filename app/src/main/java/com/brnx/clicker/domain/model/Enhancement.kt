package com.brnx.clicker.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Enhancement (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "additional_point")val additionalPoint:Int,
    @ColumnInfo(name = "point_price") var enhancementPrice:Int,
    @ColumnInfo(name = "enhancement_owned") var enhancementOwned:Int,
    @ColumnInfo(name = "point_multiplier")val pointMultiplier:Int

) {
    fun incrementOwned() {
        this.enhancementOwned++
    }

    fun updatePrice() {
        this.enhancementPrice = this.pointMultiplier * this.enhancementOwned
    }
}