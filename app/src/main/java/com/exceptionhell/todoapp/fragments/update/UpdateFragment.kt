package com.exceptionhell.todoapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.exceptionhell.todoapp.R
import com.exceptionhell.todoapp.data.modal.ToDoEntity
import com.exceptionhell.todoapp.data.viewmodal.SharedViewModal
import com.exceptionhell.todoapp.data.viewmodal.ToDoViewModal
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    val args by navArgs<UpdateFragmentArgs>()
    val sharedViewModal: SharedViewModal by viewModels()
    val toDoViewModal: ToDoViewModal by viewModels()

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_update, container, false)

        rootView.current_title_et.setText(args.currentData.title)
        rootView.current_description_et.setText(args.currentData.description)
        rootView.current_priorities_spinner.setSelection(sharedViewModal.getPriorityInt(args.currentData.priority))
        rootView.current_priorities_spinner.onItemSelectedListener = sharedViewModal.itemSelectedListener
        return rootView
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_optionmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save) {
            updateItem()
        } else if (item.itemId == R.id.delete) {
            deleteRecord()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRecord() {
        toDoViewModal.deleteRecord(args.currentData)
        Toast.makeText(requireContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun updateItem() {
        val title = rootView.current_title_et.text.toString()
        val description = rootView.current_description_et.text.toString()
        val priority = rootView.current_priorities_spinner.selectedItem.toString()

        val isValidated = sharedViewModal.isDataValid(title, description)
        if (isValidated) {
            val dataToUpdate = ToDoEntity(
                args.currentData.id,
                title,
                sharedViewModal.getPriority(priority),
                description
            )
            toDoViewModal.updateRecord(dataToUpdate)
            Toast.makeText(requireContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

    }
}