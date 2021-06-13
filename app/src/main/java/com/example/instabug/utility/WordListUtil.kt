package com.example.instabug.utility

import com.example.instabug.core.Word

object WordListUtil {

    fun reSortList(list: MutableList<Word>, isAscending: Boolean) =
        list.apply {
            if (isAscending)
                sortBy { item -> item.count }
            else
                sortByDescending { item -> item.count }
        }

}