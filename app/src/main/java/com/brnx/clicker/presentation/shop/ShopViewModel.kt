package com.brnx.clicker.presentation.shop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brnx.clicker.database.repository.EnhancementRepositoryImpl
import com.brnx.clicker.domain.model.Enhancement
import kotlinx.coroutines.launch

//@HiltViewModel
class ShopViewModel (
    private val repository: EnhancementRepositoryImpl,
    ) : ViewModel() {

    var enhancement by mutableStateOf(Enhancement(0,0,0,0,0))
        private set

    val enhancements = repository.getEnhancements()

    init {

    }
    fun getEnhancement (id:Int) = viewModelScope.launch {
        enhancement = repository.getEnhancement(id) }

    fun updateEnhancement(enhancement: Enhancement) = viewModelScope.launch {
        repository.updateEnhancement(enhancement)

    }

    fun incrementOwned(enhancement: Enhancement) {
        enhancement.incrementOwned()
        updateEnhancement(enhancement)
    }

    fun increasePrice(enhancement: Enhancement) {
        enhancement.updatePrice()
    }


}