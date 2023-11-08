package com.brnx.clicker.database.repository

import com.brnx.clicker.database.dao.EnhancementDao
import com.brnx.clicker.domain.model.Enhancement
import com.brnx.clicker.domain.repository.EnhancementRepository
import com.brnx.clicker.domain.repository.Enhancements
import kotlinx.coroutines.flow.Flow


class EnhancementRepositoryImpl (private val enhancementDao: EnhancementDao):EnhancementRepository{

    override fun getEnhancements(): Flow<Enhancements> =  enhancementDao.getEnhancements()

    override suspend fun getEnhancement(id: Int): Enhancement = enhancementDao.getEnhancementById(id)

    override suspend fun addEnhancement(enhancement: Enhancement)
    = enhancementDao.insertEnhancement(enhancement)

    override suspend fun updateEnhancement(enhancement: Enhancement)
    = enhancementDao.updateEnhancement(enhancement)

    override suspend fun deleteEnhancement(enhancement: Enhancement)
    = enhancementDao.deleteEnhancement(enhancement)



}