package com.carebearlover.learnenglish.ui.screens.list_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.adapters.ItemTouchListener
import com.carebearlover.learnenglish.adapters.WordsAdapter
import com.carebearlover.learnenglish.data.entities.WordsEntity
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(), androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {

    private lateinit var navController: NavController
    private lateinit var mAdapter: WordsAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViewModel()

        setupWordsAdapter()

        toolbar.setOnMenuItemClickListener(this)

        fb_addData.setOnClickListener {
            navController.navigate(R.id.action_listFragment_to_addWordFragment)
        }
    }

    private fun setupWordsAdapter() {
        registerForContextMenu(rv_words)
        mAdapter = WordsAdapter(
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
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

//        viewModel.getCountStudiesWords().observe(this, Observer {
//            btn_studiesButton.text = "На изучении: $it"
//        })
//
//        viewModel.getCountStudiedWords().observe(this, Observer {
//            btn_studiedButton.text = "Изучено: $it"
//        })

        viewModel.getAllStudiesWords().observe(this, Observer { list ->
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
        return false
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
