package com.magere.learnenglish.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.WordsEntity
import kotlinx.android.synthetic.main.word_item.view.*
import com.magere.learnenglish.extensions.format
import com.magere.learnenglish.extensions.today
import java.util.*

class WordsAdapter :
        RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private var mToDoList = mutableListOf<WordsEntity>()

    fun setData(wordsList: List<WordsEntity>) {
        mToDoList.clear()
        mToDoList.addAll(wordsList)

        notifyDataSetChanged()
    }

    fun insert(wordsEntity: WordsEntity) {
        mToDoList.add(wordsEntity)
        notifyItemInserted(mToDoList.size - 1)
    }

    private fun deleteToDoForSwipe(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        mToDoList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(
                        R.layout.word_item,
                        parent,
                        false
                )
        )

    }

    override fun getItemCount(): Int {
        return mToDoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = mToDoList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: WordsEntity) {
            itemView.tv_word.text = model.word
            itemView.tv_dateRepeating.text = format(model.date!!)
            when(model.group) {
                1 -> itemView.tv_countRepeating.text = " 5 раз"
                2 -> itemView.tv_countRepeating.text = " 4 раз"
                3 -> itemView.tv_countRepeating.text = " 3 раз"
                4 -> itemView.tv_countRepeating.text = " 2 раз"
                5 -> itemView.tv_countRepeating.text = " 1 раз"
                6 -> {
                    itemView.tv_dateRepeating.visibility = GONE
                    itemView.tv_needRepeat.visibility
                }
            }
        }
    }
}