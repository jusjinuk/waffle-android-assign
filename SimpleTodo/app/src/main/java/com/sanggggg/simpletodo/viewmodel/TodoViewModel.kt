package com.sanggggg.simpletodo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanggggg.simpletodo.room.Todo
import com.sanggggg.simpletodo.room.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    // TODO: Complete viewModel code
    // example: fun addTodo, fun checkTodo,
    //        : val allTodos ...

    val allTodosLiveData = repository.getAllTodos()
    var alltodos = allTodosLiveData.value

    fun addTodo(title: String, content: String) {
        val newitem = Todo(title, content)
        viewModelScope.launch{
            repository.addTodo(newitem)
        }
    }

    fun checkTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo.id)
        }
    }
}