package com.example.testapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.ActivityUserListBinding
import com.example.testapplication.ui.base.BaseActivity
import com.example.testapplication.ui.view.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {

    private val viewModel: UserListViewModel by viewModels()
    private lateinit var userListAdapter: UserListAdapter

    override fun ActivityUserListBinding.onViewCreated(savedInstanceState: Bundle?) {
        setupUI()
        observeViewModel()
        fetchUsers() // Initial data fetch
    }

    private fun ActivityUserListBinding.setupUI() {
        userListAdapter = UserListAdapter()
        rvUser.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(this@UserListActivity)
        }

        swipeRefreshLayout.setOnRefreshListener {
            // When the user swipes, call the fetchUsers function again
            fetchUsers()
        }
    }

    private fun ActivityUserListBinding.observeViewModel() {
        viewModel.userListItems.observe(this@UserListActivity) { userList ->
            userListAdapter.submitList(userList)
        }

        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (!swipeRefreshLayout.isRefreshing) {
                    // Only show the central progress bar on initial load
                    progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }

                // If the ViewModel is no longer loading, always hide the swipe-to-refresh indicator
                if (!isLoading) {
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { errorMessage ->
                if (errorMessage != null) {
                    swipeRefreshLayout.isRefreshing = false

                    showErrorDialog(message = errorMessage) {
                        fetchUsers()
                    }
                }
            }
        }
    }

    private fun fetchUsers() {
        handleNetworkAction {
            viewModel.getListUsers()
        }
    }

    override fun getBindingFactory(): (LayoutInflater) -> ActivityUserListBinding =
        ActivityUserListBinding::inflate
}