package com.magere.learnenglish.ui.screens.edit_word_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.magere.learnenglish.R
import com.magere.learnenglish.adapters.AddExampleAdapter
import com.magere.learnenglish.adapters.ExamplesAdapter
import com.magere.learnenglish.data.entities.WordsEntity
import kotlinx.android.synthetic.main.edit_word_fragment.*
import kotlinx.android.synthetic.main.edit_word_fragment.et_word
import kotlinx.android.synthetic.main.fragment_add_word.*

class EditWordFragment : Fragment() {

    private var word: String? = ""

    private lateinit var viewModel: EditWordViewModel

    private lateinit var mAdapter: ExamplesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        word = arguments!!.getString("word")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_word_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel = ViewModelProviders.of(this).get(EditWordViewModel::class.java)
        viewModel.getExamplesByWord(word!!).observe(viewLifecycleOwner, Observer {
            mAdapter.setData(it)
        })


        et_word.setText(word)
    }


    private fun setupAdapter() {
        mAdapter = ExamplesAdapter(null, application = activity!!.application)
        val layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)
        rv_examples.layoutManager = layoutManager
        rv_examples.adapter = mAdapter
    }

}
