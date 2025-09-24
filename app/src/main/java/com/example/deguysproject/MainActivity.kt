package com.example.deguysproject


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var editTextTask: EditText
    private lateinit var buttonAdd: Button

    private val todoList = mutableListOf<TodoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        editTextTask = findViewById(R.id.editTextTask)
        buttonAdd = findViewById(R.id.buttonAdd)
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(todoList) { position ->
            // Handle delete click
            todoList.removeAt(position)
            todoAdapter.notifyItemRemoved(position)
            Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupClickListeners() {
        buttonAdd.setOnClickListener {
            val taskText = editTextTask.text.toString().trim()

            if (taskText.isNotEmpty()) {
                val newTodo = TodoItem(taskText, false)
                todoList.add(newTodo)
                todoAdapter.notifyItemInserted(todoList.size - 1)
                editTextTask.text.clear()
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show()
            }
        }
    }
}