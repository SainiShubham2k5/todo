package com.exceptionhell.todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exceptionhell.todoapp.data.modal.ToDoEntity

@Database(entities = [ToDoEntity::class],version = 2,exportSchema = false)
@TypeConverters(TypeConvertors::class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun getDao() : ToDoDao
    companion object{
        @Volatile
        private var INSTANCE : ToDoDatabase ?= null

        fun getInstance(context :Context) : ToDoDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
               return tempInstance
            }

            synchronized(this){
               val instance = Room.databaseBuilder(
                   context.applicationContext,
                   ToDoDatabase::class.java,
                   "todo_database"
               ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}