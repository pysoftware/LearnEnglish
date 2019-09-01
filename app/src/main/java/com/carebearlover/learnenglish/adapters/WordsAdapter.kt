package com.carebearlover.learnenglish.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.data.entities.db_tables.WordsEntity
import com.carebearlover.learnenglish.data.repositories.MyRepository
import com.carebearlover.learnenglish.extensions.format
import com.carebearlover.learnenglish.extensions.today
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_word.*
import kotlinx.android.synthetic.main.item_word.view.*

class WordsAdapter(
        private val listener: (WordsEntity) -> Unit,
        application: Application,
        private val navController: NavController
) : RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    private val repository = MyRepository(application)

    private var mWordsList = mutableListOf<WordsEntity>()
    private var mWordsListFull= mutableListOf<WordsEntity>()

    fun setData(wordsList: List<WordsEntity>) {
        mWordsList.clear()
        mWordsList.addAll(wordsList)
        mWordsListFull.addAll(wordsList)
        notifyDataSetChanged()
    }

    fun insert(wordsEntity: WordsEntity) {
        mWordsList.add(wordsEntity)
        notifyItemInserted(mWordsList.size - 1)
    }

    private fun deleteWord(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        mWordsList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_word,
                        parent,
                        false
                )
        )

    }

    override fun getItemCount(): Int {
        return mWordsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animationRotateExpand = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rotate_arrow_expanded)
        val animationRotateExpanded = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rotate_arrow_expand)
        holder.bind(model = mWordsList[position], listener = listener)

        holder.itemView.setOnLongClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
            popupMenu.inflate(R.menu.floating_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.resetWordProgress -> {
                        repository.updateWordRepeatDate(
                                today(),
                                mWordsList[position].word,
                                1
                        )
                        true
                    }
                    R.id.changeWord -> {
                        val bundle = bundleOf(
                                "word" to mWordsList[position].word,
                                "translate" to mWordsList[position].translate,
                                "date" to mWordsList[position].date
                        )
                        navController.navigate(
                                R.id.action_listFragment_to_editWord,
                                bundle
                        )
                        true
                    }
//                    R.id.deleteWord -> {
//                        repository.deleteWord(mWordsList[position])
//                        notifyItemRemoved(position)
//                        true
//                    }
                    else -> false
                }
            }
            popupMenu.show()

            true
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(mWordsList.size)
            if (it.tv_wordTranslate.visibility == GONE) {
                it.im_expandableArrow.startAnimation(animationRotateExpand)
                it.im_expandableArrow.rotation = 180f
                it.tv_wordTranslate.visibility = VISIBLE
            } else if (it.tv_wordTranslate.visibility == VISIBLE) {
                it.im_expandableArrow.startAnimation(animationRotateExpanded)
                it.im_expandableArrow.rotation = 0f
                it.tv_wordTranslate.visibility = GONE
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(model: WordsEntity, listener: (WordsEntity) -> Unit) {

            tv_word.text = model.word
            tv_wordTranslate.text = model.translate
            tv_dateRepeating.text = format(model.date ?: 0)
            when (model.groupp) {
                1 -> tv_countRepeating.text = " 6"
                2 -> tv_countRepeating.text = " 5"
                3 -> tv_countRepeating.text = " 4"
                4 -> tv_countRepeating.text = " 3"
                5 -> tv_countRepeating.text = " 2"
                6 -> tv_countRepeating.text = " 1"
            }
            itemView.setOnClickListener {
                listener.invoke(model)
            }
        }
    }
}