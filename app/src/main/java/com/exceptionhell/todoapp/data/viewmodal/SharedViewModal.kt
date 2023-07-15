package com.exceptionhell.todoapp.data.viewmodal

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.exceptionhell.todoapp.R
import com.exceptionhell.todoapp.data.modal.Priority
import com.exceptionhell.todoapp.data.modal.ToDoEntity
import timber.log.Timber

class SharedViewModal(application: Application) : AndroidViewModel(application) {


    val isDataEmpty : MutableLiveData<Boolean> = MutableLiveData(true)


    fun checkIsDataPresent(list : List<ToDoEntity>){
        isDataEmpty.postValue(list.isEmpty())
    }
    fun getPriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH
            }
            "Medium Priority" -> {
                Priority.MEDIUM
            }
            "Low Priority" -> {
                Priority.LOW
            }
            else -> {
                Priority.HIGH
            }
        }
    }

    fun isDataValid(title: String, description: String): Boolean {
        when {
            TextUtils.isEmpty(title) || TextUtils.isEmpty(description) -> {
                Timber.e("Title is Empty")
                return false
            }
        }
        return true
    }

    val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(parent.context, R.color.red)) }
                1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(parent.context, R.color.yellow)) }
                2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(parent.context, R.color.green)) }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }

    fun getPriorityInt(priority: Priority) : Int{
        return when(priority) {
            Priority.LOW ->{
                2
            }
            Priority.MEDIUM ->{
                1
            }
            Priority.HIGH ->{
                0
            }
        }
    }


}