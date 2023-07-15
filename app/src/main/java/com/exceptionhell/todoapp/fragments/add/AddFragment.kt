package com.exceptionhell.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.exceptionhell.todoapp.R
import com.exceptionhell.todoapp.data.modal.Priority
import com.exceptionhell.todoapp.data.modal.ToDoEntity
import com.exceptionhell.todoapp.data.repos.ToDoRepository
import com.exceptionhell.todoapp.data.viewmodal.SharedViewModal
import com.exceptionhell.todoapp.data.viewmodal.ToDoViewModal
import kotlinx.android.synthetic.main.fragment_add.view.*
import timber.log.Timber

class AddFragment : Fragment() {

    lateinit var rootView: View
    val toDoViewModal : ToDoViewModal by viewModels()
    val sharedViewModel : SharedViewModal by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (!::rootView.isInitialized) {
            rootView = inflater.inflate(R.layout.fragment_add, container, false)
        }
        //set menu
        setHasOptionsMenu(true)

        initialView()
        return rootView
    }

    private fun initialView() {
        rootView.priorities_spinner.onItemSelectedListener = sharedViewModel.itemSelectedListener
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_optionmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                insertDataToDb()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val title = rootView.title_et.text.toString().trim()
        val priority = rootView.priorities_spinner.selectedItem.toString()
        val description = rootView.description_et.text.toString().trim()
        if (sharedViewModel.isDataValid(title, description)) {
            val toDoEntry = ToDoEntity(
                id = 0,
                title = title,
                priority = sharedViewModel.getPriority(priority),
                description = description
            )
            toDoViewModal.insertData(toDoEntry)
            Toast.makeText(requireContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }




}