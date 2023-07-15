package com.exceptionhell.todoapp.data.repos

import androidx.lifecycle.LiveData
import com.exceptionhell.todoapp.data.ToDoDao
import com.exceptionhell.todoapp.data.modal.ToDoEntity

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getToDoList : LiveData<List<ToDoEntity>> = toDoDao.getAllEntries()

    suspend fun insertEntry(toDoEntity: ToDoEntity){
        toDoDao.insertEntry(toDoEntity)
    }

    suspend fun updateRecord(toDoEntity: ToDoEntity){
        toDoDao.updateRecord(toDoEntity)
    }

    suspend fun deleteRecord(currentData: ToDoEntity) {
        toDoDao.deleteRecord(currentData)
    }
    suspend fun deleteAllRecords(){
        toDoDao.deleteAllRecord()
    }
}