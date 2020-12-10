package com.android.example.seminarmanager.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ItemSeminarEnrolledBinding
import com.android.example.seminarmanager.network.dto.SeminarEnrolled
import com.android.example.seminarmanager.ui.seminar.DetailSeminarActivity

class SeminarEnrolledViewHolder(private val binding: ItemSeminarEnrolledBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SeminarEnrolled) {
        binding.run {
            seminarName.text = seminar.name
            clickListener = SeminarClickListener(seminar.id) {
                binding.root.context.startActivity(
                    DetailSeminarActivity.intentWithSeminarId(binding.root.context, it)
                )
            }
        }
    }
}