package com.carebearlover.learnenglish.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import kotlinx.android.synthetic.main.item_add_example.view.*

open class AddExampleAdapter : RecyclerView.Adapter<AddExampleAdapter.ViewHolder>() {

    private val example = "Пример #"
    private var mExamplesList = mutableListOf<MyExampleEntity>()

    fun addData(myExampleEntity: MyExampleEntity) {
        mExamplesList.add(mExamplesList.size, myExampleEntity)
        notifyItemInserted(mExamplesList.lastIndex)
    }

    fun getExamples(): List<MyExampleEntity> {
        mExamplesList.removeAll{
            it.example.isNullOrBlank() && it.translateExample.isNullOrBlank()
        }
        return mExamplesList
    }

    fun setData(wordsList: List<MyExampleEntity>) {
        mExamplesList.clear()
        mExamplesList.addAll(wordsList)

        notifyDataSetChanged()
    }

    private fun deleteExample(viewHolder: ViewHolder) {
        val position = viewHolder.adapterPosition
        mExamplesList.removeAt(position)

        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                itemView = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_add_example,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int = mExamplesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.tv_numberOfExample.text = "$example ${mExamplesList.size}"

        holder.itemView.im_deleteExample.setOnClickListener {
            deleteExample(holder)
        }

        holder.itemView.et_rusWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank())
                    mExamplesList[position].translateExample = p0.toString().trim()
            }

        })

        holder.itemView.et_engWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank())
                    mExamplesList[position].example = p0.toString().trim()
            }
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}