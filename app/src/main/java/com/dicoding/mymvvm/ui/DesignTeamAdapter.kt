package com.dicoding.mymvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ItemDesignTeamBinding

// Adapter for RecyclerView to handle and display DesignTeam data
class DesignTeamAdapter : RecyclerView.Adapter<DesignTeamAdapter.ViewHolder>() {

    // Listeners for edit and delete actions
    private var onItemEditClickListener: ((DesignTeam) -> Unit)? = null
    private var onItemDeleteClickListener: ((DesignTeam) -> Unit)? = null

    /** ----------- Public Functions ------------ **/

    // Sets a listener for the edit button click event
    fun setOnItemEditClickListener(listener: (DesignTeam) -> Unit) {
        onItemEditClickListener = listener
    }

    // Sets a listener for the delete button click event
    fun setOnItemDeleteClickListener(listener: (DesignTeam) -> Unit) {
        onItemDeleteClickListener = listener
    }

    /** ----------- RecyclerView Functions ------------ **/

    // Inflates the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDesignTeamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    // Binds the data to the view for the current item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int = differ.currentList.size

    /** ----------- ViewHolder Class ------------ **/

    // ViewHolder class to bind item data and handle click events
    inner class ViewHolder(private val binding: ItemDesignTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Binds item data to UI components
        fun bind(item: DesignTeam) {
            binding.run {
                tvName.text = item.name
                tvDivision.text = item.division

                // Edit click listener
                btnEdit.setOnClickListener {
                    onItemEditClickListener?.invoke(item)
                }

                // Delete click listener
                btnDelete.setOnClickListener {
                    onItemDeleteClickListener?.invoke(item)
                }
            }
        }
    }

    /** ----------- DiffUtil for List Comparison ------------ **/

    // Callback for calculating the difference between two lists
    private val differCallBack = object : DiffUtil.ItemCallback<DesignTeam>() {
        // Checks if items represent the same entity
        override fun areItemsTheSame(oldItem: DesignTeam, newItem: DesignTeam): Boolean =
            oldItem.id == newItem.id

        // Checks if the contents of two items are the same
        override fun areContentsTheSame(oldItem: DesignTeam, newItem: DesignTeam): Boolean =
            oldItem == newItem
    }

    // DiffUtil instance to handle list updates asynchronously
    val differ = AsyncListDiffer(this, differCallBack)
}
