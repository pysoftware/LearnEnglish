package com.magere.learnenglish.adapters

import android.app.Application
import android.view.ContextMenu
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
import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.data.repositories.Repository
import com.magere.learnenglish.extensions.format
import com.magere.learnenglish.extensions.today
import kotlinx.android.synthetic.main.word_item.view.*

class WordsAdapter(
        private var mItemTouchListener: ItemTouchListener?,
        application: Application,
        private val navController: NavController
) :
        RecyclerView.Adapter<WordsAdapter.ViewHolder>() {
    var word: WordsEntity? = null
    private val repository = Repository(application)

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

    private fun deleteWord(viewHolder: ViewHolder) {
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
        val animationRotateExpand = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rotate_arrow_expanded)
        val animationRotateExpanded = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rotate_arrow_expand)
        holder.bind(model = mToDoList[position])

        holder.itemView.setOnLongClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
            popupMenu.inflate(R.menu.floating_menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.resetWordProgress -> {
                        repository.updateWordRepeatDate(
                                today(),
                                mToDoList[position].word,
                                1
                        )
                        true
                    }
                    R.id.changeWord -> {
                        val bundle = bundleOf(
                                "word" to mToDoList[position].word,
                                "translate" to mToDoList[position].translate,
                                "date" to mToDoList[position].date
                        )
                        navController.navigate(
                                R.id.action_listFragment_to_editWord,
                                bundle
                        )
                        true
                    }
                    R.id.deleteWord -> {
                        repository.deleteWord(mToDoList[position])
                        notifyItemRemoved(position)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()

            true
        }

        holder.itemView.setOnClickListener {
            notifyItemChanged(mToDoList.size)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var mModel : WordsEntity? = null

        fun bind(model: WordsEntity) {
            mModel = model
            itemView.tv_word.text = model.word
            itemView.tv_wordTranslate.text = model.translate
            itemView.tv_dateRepeating.text = format(model.date!!)
            when (model.group) {
                1 -> itemView.tv_countRepeating.text = " 6"
                2 -> itemView.tv_countRepeating.text = " 5"
                3 -> itemView.tv_countRepeating.text = " 4"
                4 -> itemView.tv_countRepeating.text = " 3"
                5 -> itemView.tv_countRepeating.text = " 2"
                6 -> itemView.tv_countRepeating.text = " 1"
            }
        }
    }
}