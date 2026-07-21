package com.example.testapplication.domain.repositories

import com.example.testapplication.domain.remote.UserListDataSource
import com.example.testapplication.model.remote.UserListRepoResponse
import com.skydoves.sandwich.ApiResponse

class UserListRepository(private val userListDataSource: UserListDataSource) {

    suspend fun getUserList(): ApiResponse<UserListRepoResponse> {
        return userListDataSource.getListUser()
    }
}