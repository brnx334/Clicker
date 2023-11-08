package com.brnx.clicker.presentation.clicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brnx.clicker.database.repository.UserRepositoryImpl
import com.brnx.clicker.domain.model.User
import com.brnx.clicker.domain.model.deductPoints
import com.brnx.clicker.domain.model.increaseAdditionalPoint
import com.brnx.clicker.domain.model.increaseScore
import com.brnx.clicker.domain.model.isEnoughBalance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//@HiltViewModel
class UserViewModel (
    private val repository: UserRepositoryImpl) : ViewModel() {

    private val _user = MutableStateFlow(User(0,0,0,false,
        isDarkModeActive = false,
        isMusicActive = false,
        isSoundActive = false
    ))
    var user: StateFlow<User> = _user.asStateFlow()


    init {
        initUser()
    }

    private fun initUser() = viewModelScope.launch {
        repository.getUser().also { _user.value = it }
    }

    private fun getUser() = viewModelScope.launch{
        repository.getUser()
    }

    private fun updateLocalUser() = viewModelScope.launch{
        repository.updateUser(_user.value)
    }

    fun increaseUserScore ():Int{
        _user.value.increaseScore()
        updateLocalUser()
        return _user.value.userScore
    }

    fun deductPoints(price: Int) {
        _user.value.deductPoints(price)
        updateLocalUser()
    }

    fun isEnoughBalance(price: Int):Boolean{
        return _user.value.isEnoughBalance(price)
    }


    fun getReadOnlyUser(): User {
        return user.value

    }

    fun increaseAdditionalPoint(additionalPoint: Int) {
        _user.value.increaseAdditionalPoint(additionalPoint)
        updateLocalUser()
    }

    fun updateUserSoundSetting(newValue: Boolean) {
        _user.value.isSoundActive = newValue
        updateLocalUser()
    }

    fun updateUserDarkModeSetting(newValue: Boolean) {
        _user.value.isDarkModeActive = newValue
        updateLocalUser()
    }
}