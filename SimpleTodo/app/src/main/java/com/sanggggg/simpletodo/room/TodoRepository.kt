package com.sanggggg.simpletodo.room

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TodoRepository(context: Context) {
    // TODO: Add the interactions between repository(MVVM Model) & viewModel(MVVM ViewModell)
    // example: fun deleteTodo(id: Long) ... fun getAllTodos(): LiveData<List<Todo>>

    val dataSource = TodoDatabase.getInstance(context).todoDao()

    suspend fun deleteTodo(id: Long){
        dataSource.deleteTodo(id)
    }

    fun getAllTodos() : LiveData<List<Todo>> {
        return dataSource.getAllTodos()
    }

    suspend fun addTodo(todo: Todo){
        dataSource.insertTodo(todo)
    }

}