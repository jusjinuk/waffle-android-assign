package com.android.example.seminarmanager.ui.seminar


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.*
import com.android.example.seminarmanager.network.dto.*
import com.android.example.seminarmanager.ui.viewholder.PersonViewHolder
import com.android.example.seminarmanager.ui.viewholder.SeminarInstructingViewHolder
import com.android.example.seminarmanager.ui.viewholder.SeminarParticipatingViewHolder
import com.android.example.seminarmanager.ui.viewholder.SeminarViewHolder
import java.lang.IllegalStateException

class PersonListAdapter() :
    ListAdapter<SeminarPerson, PersonViewHolder>(PersonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

}

class PersonDiffCallback : DiffUtil.ItemCallback<SeminarPerson>() {
    override fun areItemsTheSame(oldItem: SeminarPerson, newItem: SeminarPerson): Boolean =
        when (oldItem) {
            is SeminarParticipant -> oldItem.id == (newItem as SeminarParticipant).id
            is SeminarInstructor -> oldItem.id == (newItem as SeminarInstructor).id
        }

    override fun areContentsTheSame(oldItem: SeminarPerson, newItem: SeminarPerson): Boolean {
        return oldItem == newItem
    }
}

