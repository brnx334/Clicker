package com.brnx.clicker.domain.repository

import com.brnx.clicker.domain.model.Enhancement
import kotlinx.coroutines.flow.Flow

typealias Enhancements = List<Enhancement>


interface EnhancementRepository {

    fun getEnhancements():Flow<Enhancements>

    suspend fun getEnhancement(id:Int):Enhancement

    suspend fun addEnhancement(enhancement: Enhancement)

    suspend fun updateEnhancement(enhancement: Enhancement)

    suspend fun deleteEnhancement(enhancement: Enhancement)
}