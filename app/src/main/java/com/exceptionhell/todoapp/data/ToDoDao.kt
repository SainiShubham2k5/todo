package com.exceptionhell.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exceptionhell.todoapp.data.modal.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM todo_table")
    fun getAllEntries() : LiveData<List<ToDoEntity>>

    @Insert
    suspend fun insertEntry(toDoEntity: ToDoEntity)

    @Update
    suspend fun updateRecord(toDoEntity: ToDoEntity)

    @Delete
    suspend fun deleteRecord(currentData: ToDoEntity)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllRecord()



























}