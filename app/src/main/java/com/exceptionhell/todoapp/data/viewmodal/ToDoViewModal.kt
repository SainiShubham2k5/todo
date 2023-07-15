package com.exceptionhell.todoapp.data.viewmodal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.exceptionhell.todoapp.data.ToDoDatabase
import com.exceptionhell.todoapp.data.modal.ToDoEntity
import com.exceptionhell.todoapp.data.repos.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModal(application: Application) : AndroidViewModel(application) {
    private val toDoDao = ToDoDatabase.getInstance(application).getDao()
    private val repo : ToDoRepository
    val getAllData : LiveData<List<ToDoEntity>>

    init {
        repo = ToDoRepository(toDoDao)
        getAllData = repo.getToDoList
    }

    fun insertData(toDoEntity: ToDoEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertEntry(toDoEntity)
        }
    }

    fun updateRecord(toDoEntity: ToDoEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateRecord(toDoEntity)
        }
    }

    fun deleteRecord(currentData: ToDoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteRecord(currentData)
        }
    }

    fun deleteAllRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllRecords()
        }
    }


}