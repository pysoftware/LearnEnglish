package com.magere.learnenglish.adapters

import android.view.View
import com.magere.learnenglish.data.entities.WordsEntity

interface ItemTouchListener {
    fun onLongItemClick(view: View)
    fun onItemClick(wordsEntity: WordsEntity)
}