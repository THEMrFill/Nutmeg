package com.nutmeg.philip.arnold.mainpage

import com.nutmeg.philip.arnold.retrofit.data.User

interface UserResponseInterface {
    fun ReturnUsers(newUsers: ArrayList<User>, fromRx: Boolean)
    fun ReturnError(error: Throwable)
}