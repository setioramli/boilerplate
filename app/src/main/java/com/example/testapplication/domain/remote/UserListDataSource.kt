package com.example.testapplication.domain.remote

import com.example.testapplication.model.remote.UserListRepoResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class UserListDataSource @Inject constructor(
    private val userListService: UserListService
) {

    suspend fun getListUser(): ApiResponse<UserListRepoResponse> {
        return userListService.getUserListRepos()
    }
}