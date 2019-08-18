package com.magere.learnenglish.ui.screens.add_word_screen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.ui.screens.list_screen.ListViewModel
import kotlinx.android.synthetic.main.addword_dialog.*

class AddWordFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        navController = Navigation.findNavController(view)
        btn_accept.setOnClickListener {

            viewModel.insertWord(
                    WordsEntity(
                            id = null,
                            word = et_word.text.toString(),
                            translate = et_translate.text.toString(),
                            example1 = et_wordExample.text.toString(),
                            translateExample1 = et_wordTranslate.text.toString()
                    )
            )
            navController.navigate(R.id.action_addWordFragment_to_listFragment)
        }

        btn_cancel.setOnClickListener {
            navController.navigate(R.id.action_addWordFragment_to_listFragment)
        }
    }


}
