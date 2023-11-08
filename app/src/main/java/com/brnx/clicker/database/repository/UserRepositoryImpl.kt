package com.brnx.clicker.database.repository

import com.brnx.clicker.database.dao.UserDao
import com.brnx.clicker.domain.model.User
import com.brnx.clicker.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {

    override suspend fun getUser(): User = userDao.getUser()

    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)
}
