package com.magere.learnenglish.ui.screens.add_word_screen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.magere.learnenglish.R
import com.magere.learnenglish.adapters.AddExampleAdapter
import com.magere.learnenglish.data.entities.ExampleEntity
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.ui.screens.list_screen.ListViewModel
import kotlinx.android.synthetic.main.fragment_add_word.*
import kotlinx.android.synthetic.main.list_fragment.*

class AddWordFragment : Fragment() {

    private lateinit var mAdapter: AddExampleAdapter
    private lateinit var navController: NavController
    private lateinit var viewModel: ListViewModel

    private val mExamples = mutableListOf<ExampleEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        navController = Navigation.findNavController(view)
        btn_accept.setOnClickListener {

            viewModel.insertExamples(mAdapter.getExamples())

            viewModel.insertWord(
                    WordsEntity(
                            id = null,
                            word = et_word.text.toString(),
                            translate = et_translate.text.toString()
                    )
            )
            navController.navigate(R.id.action_addWordFragment_to_listFragment)
        }

        btn_cancel.setOnClickListener {
            navController.navigate(R.id.action_addWordFragment_to_listFragment)
        }

        btn_addExample.setOnClickListener {
            mAdapter.addData(ExampleEntity(word = et_word.text.toString()))
        }
    }

    private fun setupAdapter() {
        mAdapter = AddExampleAdapter()
        val layoutManager = LinearLayoutManager(view!!.context, RecyclerView.VERTICAL, false)
        rv_addExample.layoutManager = layoutManager
        mAdapter.setData(mExamples)
        rv_addExample.adapter = mAdapter
    }

}
