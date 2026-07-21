package com.example.testapplication.model.local

import java.io.Serializable

data class UserModel(
    val email: String,
    val name: String,
    val id: Int
): Serializable

fun dummyUser(): UserModel = UserModel(
    "firwandi.ramli@gmail.com",
    "firwandi",
    0
)