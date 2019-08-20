package com.magere.learnenglish.ui.screens.edit_word_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.WordsEntity
import kotlinx.android.synthetic.main.edit_word_fragment.*

class EditWord : Fragment() {

    private var word: String? = ""

    private lateinit var viewModel: EditWordViewModel

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
        viewModel = ViewModelProviders.of(this).get(EditWordViewModel::class.java)

        tv_word.text = word
    }

}
