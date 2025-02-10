package com.dicoding.mymvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ItemDesignTeamBinding


class DesignTeamAdapter : RecyclerView.Adapter<DesignTeamAdapter.ViewHolder>() {

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

                binding.root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DesignTeam>() {
        override fun areItemsTheSame(
            oldExampleModel: DesignTeam, newExampleModel: DesignTeam
        ): Boolean {
            return oldExampleModel.id == newExampleModel.id
        }

        override fun areContentsTheSame(
            oldExampleModel: DesignTeam, newExampleModel: DesignTeam
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((DesignTeam) -> Unit)? = null

    fun setOnItemClickListener(listener: (DesignTeam) -> Unit) {
        onItemClickListener = listener
    }

}
