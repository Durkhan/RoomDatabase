package com.example.againroomdatabase.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

   val readAllData:LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user:User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUser(){
        userDao.deleteAllUsers()
    }
}