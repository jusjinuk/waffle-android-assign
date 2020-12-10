package com.android.example.seminarmanager.ui.main.user


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.example.seminarmanager.databinding.*
import com.android.example.seminarmanager.network.dto.SeminarEnrolled
import com.android.example.seminarmanager.ui.viewholder.SeminarEnrolledViewHolder

class SeminarHorizontalListAdapter() :
    ListAdapter<SeminarEnrolled, SeminarEnrolledViewHolder>(SeminarEnrolledDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeminarEnrolledViewHolder =
        SeminarEnrolledViewHolder(
            ItemSeminarEnrolledBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: SeminarEnrolledViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

}

class SeminarEnrolledDiffCallback : DiffUtil.ItemCallback<SeminarEnrolled>() {
    override fun areItemsTheSame(oldItem: SeminarEnrolled, newItem: SeminarEnrolled): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SeminarEnrolled, newItem: SeminarEnrolled): Boolean {
        return oldItem == newItem
    }
}

