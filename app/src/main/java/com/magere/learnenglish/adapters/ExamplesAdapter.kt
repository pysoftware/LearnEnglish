package com.magere.learnenglish.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.ExampleEntity
import com.magere.learnenglish.data.repositories.Repository
import kotlinx.android.synthetic.main.example_item.view.*


class ExamplesAdapter(
        private var mItemTouchListener: ItemTouchListener?,
        application: Application
) : RecyclerView.Adapter<ExamplesAdapter.ViewHolder>() {

    //    var word: WordsEntity? = null
    private val repository = Repository(application)

    private var mExamplesList = mutableListOf<ExampleEntity>()

    fun setData(wordsList: List<ExampleEntity>) {
        mExamplesList.clear()
        mExamplesList.addAll(wordsList)

        notifyDataSetChanged()
    }

    private fun deleteWord(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        mExamplesList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(
                        R.layout.example_item,
                        parent,
                        false
                )
        )

    }

    override fun getItemCount(): Int {
        return mExamplesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mExamplesList[position])

        holder.itemView.setOnLongClickListener {
            showAlertDialog(holder, mExamplesList[position])
            true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: ExampleEntity) {
            itemView.tv_example.text = model.example
            itemView.tv_translateExample.text = model.translateExample
        }
    }

    private fun showAlertDialog(viewHolder: ViewHolder, example: ExampleEntity) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(viewHolder.itemView.context)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setTitle("Вы уверены, что хотите удалить этот пример?")
        alertDialogBuilder.setPositiveButton("Да") { _, _ ->
            repository.deleteExample(example)
            deleteWord(viewHolder)
        }
        alertDialogBuilder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
    }
}