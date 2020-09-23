package com.sanggggg.simpletodo.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanggggg.simpletodo.databinding.ItemTodoBinding
import com.sanggggg.simpletodo.room.Todo
import com.sanggggg.simpletodo.viewmodel.TodoViewModel

// TODO: Complete TodoListAdapter
class TodoListAdapter(private val viewModel: TodoViewModel) : RecyclerView.Adapter<TodoViewHolder>() {

    var data = listOf<Todo>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = data[position]
        holder.bindTodo(item)
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