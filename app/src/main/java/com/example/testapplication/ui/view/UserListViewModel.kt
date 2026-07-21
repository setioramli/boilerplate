package com.example.testapplication.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.repositories.UserListRepository
import com.example.testapplication.model.remote.UserListRepoResponseItem
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListRepository: UserListRepository
): ViewModel(){

    private val _userListItems = MutableLiveData<List<UserListRepoResponseItem>>()
    val userListItems: LiveData<List<UserListRepoResponseItem>> get() =_userListItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getListUsers() {
        // 1. Launch a coroutine in the ViewModel's lifecycle-aware scope.
        viewModelScope.launch {
            // 2. Set initial states on the Main thread before the network call.
            _isLoading.value = true
            _error.value = null

            // 3. Make the network request. userListRepository.getUserList() is a suspend function.
            userListRepository.getUserList()
                .onSuccess {
                    // 4. On success, update the LiveData with the user list.
                    // This block executes on the Main thread, so we use .value
                    _userListItems.value = this.data
                }
                .onError {
                    // 5. On error, update the error state with a message from the response.
                    _error.value = this.message()
                }

            // 6. After the request is complete (either success or error), turn off the loading indicator.
            _isLoading.value = false
        }
    }
}