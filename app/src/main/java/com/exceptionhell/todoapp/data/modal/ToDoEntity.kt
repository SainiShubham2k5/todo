package com.exceptionhell.todoapp.data.modal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exceptionhell.todoapp.data.modal.Priority
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "todo_table")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val priority: Priority,
    val description : String
):Parcelable
