package com.carebearlover.learnenglish.ui.screens.my_words_screen

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.adapters.MyWordsAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class MyWordsFragment : Fragment(),
        androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {

    private lateinit var navController: NavController
    private lateinit var mAdapter: MyWordsAdapter
    private lateinit var viewModel: MyWordsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(view)
        initViewModel()

        initWordsAdapter()

        toolbar.setOnMenuItemClickListener(this)

        fb_add_word.setOnClickListener {
            navController.navigate(R.id.action_myWordsFragment_to_addWordFragment)
        }
    }

    private fun initWordsAdapter() {
        registerForContextMenu(rv_words)
        mAdapter = MyWordsAdapter(
                application = activity!!.application,
                navController = navController,
                listener = {

                }
        )

        with(rv_words) {
            layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MyWordsViewModel::class.java)

        viewModel.getAllStudiesWords().observe(viewLifecycleOwner, Observer { list ->
            mAdapter.setData(list.sortedBy {
                it.group
            })
        })
    }

    override fun onMenuItemClick(menuItem: MenuItem?): Boolean {
        when (menuItem!!.itemId) {
            R.id.resetAllProgress -> showResetAllProgressAlert()
            R.id.deleteAllWords -> showDeleteAllWordsAlert()
        }
        return true
    }

    private fun showResetAllProgressAlert() {
        AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle("Сбросить весь прогресс?")
                .setMessage("Прогресс изучения будет сброшен")
                .setPositiveButton("Да") { _, _ ->
                    viewModel.resetAllProgress()
                    mAdapter.notifyDataSetChanged()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
    }

    private fun showDeleteAllWordsAlert() {
        AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle("Удалить все слова?")
                .setMessage("Все слова будут удалены")
                .setPositiveButton("Да") { _, _ ->
                    viewModel.deleteAllWords()
                    mAdapter.notifyDataSetChanged()
                }
                .setNegativeButton("Отмена") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
    }

}
