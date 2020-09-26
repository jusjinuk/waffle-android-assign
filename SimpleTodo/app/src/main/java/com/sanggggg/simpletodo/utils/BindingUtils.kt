package com.sanggggg.simpletodo.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanggggg.simpletodo.room.Todo
import com.sanggggg.simpletodo.ui.TodoListAdapter

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

// TODO: Bind LiveData<List<Todo>> by BindingAdapter
@BindingAdapter("items")
fun bindItems(view: RecyclerView, items: List<Todo>?){
//    Log.i("BindingUtils", items?.value.toString())
    val adapt = view.adapter as TodoListAdapter
    if(items != null){
        adapt.data = items
    }
}