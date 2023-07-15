package com.exceptionhell.todoapp.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exceptionhell.todoapp.R
import com.exceptionhell.todoapp.data.viewmodal.SharedViewModal
import com.exceptionhell.todoapp.data.viewmodal.ToDoViewModal
import com.exceptionhell.todoapp.databinding.FragmentListBinding
import com.exceptionhell.todoapp.fragments.list.adapter.ListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private val toDoViewModal: ToDoViewModal by viewModels()
    private val sharedViewModal: SharedViewModal by viewModels()

    private var _binding : FragmentListBinding ?= null
    private val binding get() = _binding!!

    val adapter: ListAdapter by lazy {
        ListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = FragmentListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.sharedVideModal = sharedViewModal


       //set up recyclerview
        setUpRecyclerView()

        toDoViewModal.getAllData.observe(viewLifecycleOwner) {
            sharedViewModal.checkIsDataPresent(it)
            adapter.setData(it)
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.rvList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_optionmenu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all -> {
                deleteAllData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllData() {
        toDoViewModal.deleteAllRecord()
        Toast.makeText(requireContext(), "Data Cleared", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}