package com.magere.learnenglish.adapters

interface ItemTouchListener {
    fun onItemClick(position: Int)

    fun onRightMenuClick(id: Int?)
}