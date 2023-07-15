package com.exceptionhell.todoapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.exceptionhell.todoapp.R
import com.exceptionhell.todoapp.data.modal.Priority
import com.exceptionhell.todoapp.data.modal.ToDoEntity
import com.exceptionhell.todoapp.fragments.list.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    var list = emptyList<ToDoEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_title.text = list[position].title
        holder.itemView.tv_decsription.text = list[position].description
        when(list[position].priority){
            Priority.LOW -> {
                holder.itemView.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green))
            }
            Priority.MEDIUM -> {
                holder.itemView.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow))
            }
            Priority.HIGH -> {
                holder.itemView.priority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red))
            }
        }

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(list[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = list.size

    fun setData(dataList : List<ToDoEntity>){
        this.list = dataList
        notifyDataSetChanged()
    }

}