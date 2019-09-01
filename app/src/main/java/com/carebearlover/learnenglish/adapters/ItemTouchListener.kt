package com.carebearlover.learnenglish.adapters

import android.view.View
import com.carebearlover.learnenglish.data.entities.MyWordsEntity

interface ItemTouchListener {
    fun onLongItemClick(view: View)
    fun onItemClick(wordsEntity: MyWordsEntity)
}