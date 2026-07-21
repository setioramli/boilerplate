package com.example.testapplication.model.remote

import com.google.gson.annotations.SerializedName

data class UserListRepoResponseItem (
    @SerializedName("name")
    val name: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("id")
    val id: Int = 0
)