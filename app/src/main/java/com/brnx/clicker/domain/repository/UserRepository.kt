package com.brnx.clicker.domain.repository

import com.brnx.clicker.domain.model.User


interface UserRepository {

    suspend fun getUser(): User

    suspend fun insertUser(user: User)

    suspend fun updateUser(user:User)


}