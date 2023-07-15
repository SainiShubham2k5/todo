package com.exceptionhell.todoapp.fragments.databindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.exceptionhell.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object {
        @BindingAdapter("android:navToAdd")
        @JvmStatic
        fun navToAddFragment(view: FloatingActionButton, isNavigate: Boolean) {
            view.setOnClickListener {
                if (isNavigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyData")
        @JvmStatic
        fun emptyDataBase(view: View, databaseList: MutableLiveData<Boolean>) {
            when (databaseList.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.GONE
            }
        }

    }

}