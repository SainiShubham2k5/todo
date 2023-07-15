package com.exceptionhell.todoapp.data

import androidx.room.TypeConverter
import com.exceptionhell.todoapp.data.modal.Priority


class TypeConvertors {

    @TypeConverter
    fun getPriorityToString(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun getStringToPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}