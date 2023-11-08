package com.brnx.clicker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.domain.repository.Enhancements
import kotlinx.coroutines.flow.Flow
@Dao
interface EnhancementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEnhancement(enhancement: Enhancement)

    @Query("SELECT * FROM Enhancement")
    fun getEnhancements(): Flow<Enhancements>

    @Query("SELECT * FROM Enhancement WHERE id = :id")
    fun getEnhancementById(id: Int): Enhancement

    @Update
    fun updateEnhancement(enhancement: Enhancement)

    @Delete
    fun deleteEnhancement(enhancement: Enhancement)
}