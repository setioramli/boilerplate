package com.example.testapplication.domain.remote

import com.example.testapplication.model.remote.UserListRepoResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface UserListService {

    @GET("/users")
    suspend fun getUserListRepos(): ApiResponse<UserListRepoResponse>
}