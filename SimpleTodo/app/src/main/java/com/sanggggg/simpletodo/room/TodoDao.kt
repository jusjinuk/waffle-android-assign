package com.sanggggg.simpletodo.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TodoDao {
    // TODO: Add the queries(interaction) on the specification
    // example - fun deleteTodo ... fun getAllTodos ...

    @Insert
    suspend fun insertTodo(todo: Todo) : Long

    @Query("DELETE FROM todo_table WHERE id = :id")
    suspend fun deleteTodo(id: Long)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos() : LiveData<List<Todo>>


}