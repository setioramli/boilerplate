package com.example.testapplication.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.text
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.ItemListUserBinding
import com.example.testapplication.model.remote.UserListRepoResponseItem

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    // Holds the list of users.
    private var userList: List<UserListRepoResponseItem> = emptyList()

    // Creates a new ViewHolder when the RecyclerView needs one.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // 2. Inflate the layout using the binding class
        val binding = ItemListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    // Binds the data to the ViewHolder for a specific position.
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    // Returns the total number of items in the list.
    override fun getItemCount(): Int = userList.size

    // 3. The ViewHolder now holds a reference to the binding, not the View.
    class UserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserListRepoResponseItem) {
            // 4. Access views directly from the binding object
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }
    }

    // A helper function to update the adapter's data and refresh the list.
    fun submitList(newList: List<UserListRepoResponseItem>) {
        userList = newList
        notifyDataSetChanged() // Refreshes the RecyclerView.
    }
}