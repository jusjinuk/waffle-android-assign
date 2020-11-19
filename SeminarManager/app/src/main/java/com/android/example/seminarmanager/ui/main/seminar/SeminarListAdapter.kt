package com.android.example.seminarmanager.ui.main.seminar


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ActivityMainBinding
import com.android.example.seminarmanager.databinding.ItemSeminarBinding
import com.android.example.seminarmanager.databinding.ItemSeminarInstructingBinding
import com.android.example.seminarmanager.databinding.ItemSeminarParticipatingBinding
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.ui.viewholder.SeminarInstructingViewHolder
import com.android.example.seminarmanager.ui.viewholder.SeminarParticipatingViewHolder
import com.android.example.seminarmanager.ui.viewholder.SeminarViewHolder
import java.lang.IllegalStateException

class SeminarListAdapter(user: UserDto?) :
    ListAdapter<SimpleSeminarDto, RecyclerView.ViewHolder>(SeminarDiffCallback()) {

    val seminar_participating = user?.participant?.seminars?.map { it.id }
    val seminar_instructing = user?.instructor?.charge?.map { it.id }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            SEMINAR_DEFAULT_VIEW_TYPE -> {
                SeminarViewHolder(
                    ItemSeminarBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            SEMINAR_PARTICIPATING_VIEW_TYPE -> {
                SeminarParticipatingViewHolder(
                    ItemSeminarParticipatingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            SEMINAR_INSTRUCTING_VIEW_TYPE -> {
                SeminarInstructingViewHolder(
                    ItemSeminarInstructingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalStateException("Viewtype error")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SeminarViewHolder -> holder.bindItems(getItem(position))
            is SeminarInstructingViewHolder -> holder.bindItems(getItem(position))
            is SeminarParticipatingViewHolder -> holder.bindItems(getItem(position))

        }
    }

    override fun getItemViewType(position: Int): Int = when {
        seminar_participating?.contains(getItem(position).id) == true ->
            SEMINAR_PARTICIPATING_VIEW_TYPE
        seminar_instructing?.contains(getItem(position).id) == true ->
            SEMINAR_INSTRUCTING_VIEW_TYPE
        else ->
            SEMINAR_DEFAULT_VIEW_TYPE
    }

    companion object {
        private const val SEMINAR_DEFAULT_VIEW_TYPE = 0
        private const val SEMINAR_PARTICIPATING_VIEW_TYPE = 1
        private const val SEMINAR_INSTRUCTING_VIEW_TYPE = 2
    }
}

class SeminarDiffCallback : DiffUtil.ItemCallback<SimpleSeminarDto>() {
    override fun areItemsTheSame(oldItem: SimpleSeminarDto, newItem: SimpleSeminarDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SimpleSeminarDto, newItem: SimpleSeminarDto): Boolean {
        return oldItem == newItem
    }
}

