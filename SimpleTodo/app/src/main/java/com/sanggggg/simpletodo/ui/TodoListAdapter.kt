package com.sanggggg.simpletodo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanggggg.simpletodo.databinding.ItemTodoBinding
import com.sanggggg.simpletodo.room.Todo
import com.sanggggg.simpletodo.viewmodel.TodoViewModel

// TODO: Complete TodoListAdapter
class TodoListAdapter(private val viewModel: TodoViewModel) : ListAdapter<Todo, TodoViewHolder>(TodoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindTodo(item)
    }


}

class TodoDiffCallback: DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem==newItem
    }

}


// TODO: Complete TodoViewHolder
class TodoViewHolder(private val binding: ItemTodoBinding, private val viewModel: TodoViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bindTodo(todo: Todo) {
        binding.item  = todo
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}