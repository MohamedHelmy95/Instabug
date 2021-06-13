package com.example.instabug.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instabug.R
import com.example.instabug.core.Word
import com.example.instabug.utility.WordListUtil.reSortList


class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>(), Filterable {
    private val wordList = mutableListOf<Word>()
    private val wordListFiltered = mutableListOf<Word>()
    private var isAscending = true

    fun reSort() {
        reSortList(list = wordListFiltered, isAscending = isAscending)
        notifyDataSetChanged()
        isAscending = !isAscending
    }


    fun submitList(data: List<Word>) {

        wordList.clear()
        wordList.addAll(data)

        wordListFiltered.clear()
        wordListFiltered.addAll(data)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, pos: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(
            R.layout.word_item,
            viewGroup,
            false
        )
    )

    override fun getItemCount(): Int = wordListFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        val item = wordList[pos]
        holder.run {
            wordNameTv.text = item.name
            wordCountTv.text = item.count.toString()
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordNameTv: TextView = itemView.findViewById(R.id.wordNameTv)
        val wordCountTv: TextView = itemView.findViewById(R.id.wordCountTv)
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val query = charSequence?.toString()
                val newList =
                    if (query.isNullOrEmpty()) wordList else wordList.filter { item ->
                        item.name.contains(query, true)
                    }

                val filterResults = FilterResults()
                filterResults.values = newList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                wordListFiltered.clear()
                wordListFiltered.addAll(filterResults.values as MutableList<Word>)
                notifyDataSetChanged()
            }
        }
    }
}