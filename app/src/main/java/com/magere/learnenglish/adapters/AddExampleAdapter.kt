package com.magere.learnenglish.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.ExampleEntity
import kotlinx.android.synthetic.main.add_example_item.view.*

open class AddExampleAdapter : RecyclerView.Adapter<AddExampleAdapter.ViewHolder>() {

    private var mExamplesList = mutableListOf<ExampleEntity>()

    fun addData(exampleEntity: ExampleEntity) {
        mExamplesList.add(exampleEntity)
        notifyItemInserted(mExamplesList.size)
    }

    fun getExamples(): List<ExampleEntity> = mExamplesList

    fun setData(wordsList: List<ExampleEntity>) {
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
                        R.layout.add_example_item,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return mExamplesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

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