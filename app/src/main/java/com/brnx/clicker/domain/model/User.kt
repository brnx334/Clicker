package com.brnx.clicker.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
        @PrimaryKey (autoGenerate = true) val userID: Int,
        @ColumnInfo(name = "user_score")  var userScore: Int,
        @ColumnInfo(name = "user_score_per_click")  var userScorePerClick: Int,
        @ColumnInfo(name = "on_boarding_finished")var onBoardingFinished: Boolean,
        @ColumnInfo(name = "is_dark_mode_active")var isDarkModeActive: Boolean,
        @ColumnInfo(name = "is_music_active")var isMusicActive: Boolean,
        @ColumnInfo(name = "is_sound_active")var isSoundActive: Boolean,
)

fun User.increaseScore (){
        this.userScore += userScorePerClick
}

fun User.isEnoughBalance(price:Int): Boolean{
        return this.userScore >= price
}

fun User.deductPoints(price: Int){
        this.userScore -= price
}

fun User.increaseAdditionalPoint(points: Int){
        this.userScorePerClick += points
}
fun User.switchDarkMode(){
        this.isDarkModeActive = !this.isDarkModeActive
}
fun User.switchMusic(){
        this.isMusicActive = !this.isMusicActive
}
fun User.switchSound(){
        this.isSoundActive = !this.isSoundActive
}

