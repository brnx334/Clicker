package com.brnx.clicker.database.connect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brnx.clicker.R
import com.brnx.clicker.database.dao.EnhancementDao
import com.brnx.clicker.database.dao.UserDao
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.domain.model.User

@Database(entities = [User::class, Enhancement::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun enhancementDao(): EnhancementDao

    companion object {
        private var instance: com.brnx.clicker.database.connect.AppDatabase? = null

        fun getInstance(context: Context, isDarkModeActive: Boolean): com.brnx.clicker.database.connect.AppDatabase {



            val databaseFile = context.getDatabasePath(context.getString(R.string.app_database))
            if (!databaseFile.exists()) {
                // if db doesn't exist creating it
                com.brnx.clicker.database.connect.AppDatabase.Companion.instance = Room
                    .databaseBuilder(context, com.brnx.clicker.database.connect.AppDatabase::class.java, context.getString(R.string.app_database))
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                    //init db
                com.brnx.clicker.database.connect.AppDatabase.Companion.instance?.let { db ->
                    // User init
                    val userDao = db.userDao()
                    val user = User(1, 0, 1,
                        onBoardingFinished = false,
                        isDarkModeActive = isDarkModeActive,
                        isMusicActive = true,
                        isSoundActive = true
                    )
                    userDao.insertUser(user)

                    //enhancement init
                    val enhancementDao = db.enhancementDao()

                    val enhancement1 = Enhancement(1,1, 100,0, 100)
                    val enhancement2 = Enhancement(2,5, 500,0, 500)
                    val enhancement3 = Enhancement(3,7, 1000,0, 1000)
                    val enhancement4 = Enhancement(4,10, 5000,0, 5000)
                    val enhancement5 = Enhancement(5,15, 10000,0, 10000)

                    enhancementDao.insertEnhancement(enhancement1)
                    enhancementDao.insertEnhancement(enhancement2)
                    enhancementDao.insertEnhancement(enhancement3)
                    enhancementDao.insertEnhancement(enhancement4)
                    enhancementDao.insertEnhancement(enhancement5)

                }
            } else {
                // connecting to db if exists
                com.brnx.clicker.database.connect.AppDatabase.Companion.instance = Room
                    .databaseBuilder(context, com.brnx.clicker.database.connect.AppDatabase::class.java, context.getString(R.string.app_database))
                    .allowMainThreadQueries()
                    .build()
            }
            return com.brnx.clicker.database.connect.AppDatabase.Companion.instance as com.brnx.clicker.database.connect.AppDatabase
        }
    }


}