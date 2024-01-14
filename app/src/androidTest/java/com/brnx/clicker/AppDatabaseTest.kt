package com.brnx.clicker

import android.annotation.SuppressLint
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.brnx.clicker.database.connect.AppDatabase
import com.brnx.clicker.database.dao.EnhancementDao
import com.brnx.clicker.database.dao.UserDao
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.domain.model.User
import com.google.common.truth.Truth.*
import org.junit.After
import org.junit.Before
import org.junit.Test




internal class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var enhancementDao: EnhancementDao


    @Before
    fun setupDatabase() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        userDao = db.userDao()
        enhancementDao = db.enhancementDao()
    }

    @After
    fun closeDatabase() {
        db.close()
    }
    @SuppressLint("CheckResult")
    @Test
    fun writeAndReadUser() {
        val user = User(
            userID = 1,
            userScore = 100,
            userScorePerClick = 3,
            onBoardingFinished = true,
            isDarkModeActive = true,
            isMusicActive = true,
            isSoundActive = true
        )
        userDao.insertUser(user)
        val userFromDB = userDao.getUser()
        assertThat(userFromDB.equals(user))
    }

    @SuppressLint("CheckResult")
    @Test
    fun writeAndReadEnhancement() {
        val enhancement = Enhancement(
            id = 1,
            additionalPoint = 2,
            enhancementPrice = 100,
            enhancementOwned = 1,
            pointMultiplier = 3
        )
        enhancementDao.insertEnhancement(enhancement)
        val enhancementFromDB = enhancementDao.getEnhancementById(1)
        assertThat(enhancementFromDB.equals(enhancement))
    }

}