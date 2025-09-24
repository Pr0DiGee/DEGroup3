package com.example.deguysproject

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todoList: MutableList<TodoItem>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxTask)
        val textViewTask: TextView = itemView.findViewById(R.id.textViewTask)
        val buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoItem = todoList[position]

        holder.textViewTask.text = todoItem.task
        holder.checkBox.isChecked = todoItem.isCompleted

        // Strike through completed tasks
        if (todoItem.isCompleted) {
            holder.textViewTask.paintFlags = holder.textViewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.textViewTask.paintFlags = holder.textViewTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        // Handle checkbox clicks
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            todoItem.isCompleted = isChecked
            notifyItemChanged(position)
        }

        // Handle delete button clicks
        holder.buttonDelete.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = todoList.size
}