package com.magere.learnenglish.ui.screens.list_screen

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magere.learnenglish.R
import com.magere.learnenglish.adapters.ItemTouchListener
import com.magere.learnenglish.adapters.WordsAdapter
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.extensions.today
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {


    private lateinit var mTouchListener: ItemTouchListener
    private lateinit var navController: NavController
    private lateinit var mAdapter: WordsAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        navController = Navigation.findNavController(view)

        mTouchListener = object : ItemTouchListener {
            override fun onLongItemClick(view: View) {

            }

            override fun onItemClick(wordsEntity: WordsEntity) {

            }
        }
        setupWordsAdapter(mTouchListener)

        viewModel.getCountStudiesWords().observe(this, Observer {
            btn_studiesButton.text = "На изучении: $it"
        })
        viewModel.getCountStudiedWords().observe(this, Observer {
            btn_studiedButton.text = "Изучено: $it"
        })
        viewModel.getAllStudiesWords().observe(this, Observer {
            mAdapter.setData(it)
        })

        fb_addData.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_addWordFragment)
        }
    }

    private fun setupWordsAdapter(mTouchListener: ItemTouchListener) {
        registerForContextMenu(rv_words)
        mAdapter = WordsAdapter(mTouchListener, activity!!.application, navController)
        val layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
        rv_words.layoutManager = layoutManager
        rv_words.adapter = mAdapter
    }
}
