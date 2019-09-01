package com.carebearlover.learnenglish.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.repositories.MyRepository
import kotlinx.android.synthetic.main.item_example.view.*


class ExamplesAdapter(
        application: Application
) : RecyclerView.Adapter<ExamplesAdapter.ViewHolder>() {

    //    var word: MyWordsEntity? = null
    private val repository = MyRepository(application)

    private var mExamplesList = mutableListOf<MyExampleEntity>()

    fun setData(wordsList: List<MyExampleEntity>) {
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
                        R.layout.item_example,
                        parent,
                        false
                )
        )

    }

    override fun getItemCount(): Int = mExamplesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mExamplesList[position])

        holder.itemView.setOnLongClickListener {
            showAlertDialog(holder, mExamplesList[position])
            true
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: MyExampleEntity) {
            itemView.tv_example.text = model.example
            itemView.tv_translateExample.text = model.translateExample
        }
    }

    private fun showAlertDialog(viewHolder: ViewHolder, myExample: MyExampleEntity) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(viewHolder.itemView.context)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setTitle("Вы уверены, что хотите удалить этот пример?")
        alertDialogBuilder.setPositiveButton("Да") { _, _ ->
            repository.deleteExample(myExample)
            deleteWord(viewHolder)
        }
        alertDialogBuilder.setNegativeButton("Нет") { dialog, _ ->
            dialog.dismiss()
        }
    }
}