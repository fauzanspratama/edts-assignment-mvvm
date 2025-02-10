package com.dicoding.mymvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ItemDesignTeamBinding

class DesignTeamAdapter : RecyclerView.Adapter<DesignTeamAdapter.ViewHolder>() {

    private var onItemEditClickListener: ((DesignTeam) -> Unit)? = null
    private var onItemDeleteClickListener: ((DesignTeam) -> Unit)? = null

    fun setOnItemEditClickListener(listener: (DesignTeam) -> Unit) {
        onItemEditClickListener = listener
    }

    fun setOnItemDeleteClickListener(listener: (DesignTeam) -> Unit) {
        onItemDeleteClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDesignTeamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemDesignTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DesignTeam) {
            binding.run {
                tvName.text = item.name
                tvDivision.text = item.division

                // Edit click listener
                btnEdit.setOnClickListener {
                    onItemEditClickListener?.invoke(item)
                }

                // Delete button click listener
                btnDelete.setOnClickListener {
                    onItemDeleteClickListener?.invoke(item)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DesignTeam>() {
        override fun areItemsTheSame(
            oldItem: DesignTeam, newItem: DesignTeam
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: DesignTeam, newItem: DesignTeam
        ): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)
}