package com.example.instabug.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instabug.R
import com.example.instabug.core.Word


class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    private val list = mutableListOf<Word>()


    fun changeSort(isAscending: Boolean) {
        val list = list.apply {
            if (isAscending)
                list.sortBy { itemCount }
            else
                list.sortByDescending { itemCount }
        }
        submitList(list)
    }

    fun submitList(data: List<Word>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.word_item,
            viewGroup,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = list[pos]
        holder.run {
            wordNameTv.text = item.name
            wordCountTv.text = item.count.toString()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordNameTv: TextView = itemView.findViewById(R.id.wordNameTv)
        val wordCountTv: TextView = itemView.findViewById(R.id.wordCountTv)
    }

}