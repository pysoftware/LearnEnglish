package com.carebearlover.learnenglish.ui.screens.category_list_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.adapters.CategoriesAdapter
import com.carebearlover.learnenglish.adapters.WordsAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_list.*

class CategoriesFragment : Fragment() {

    private lateinit var mCategoriesAdapter: CategoriesAdapter
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        registerForContextMenu(rv_categories)
        mCategoriesAdapter = CategoriesAdapter(navController = navController)

        with(rv_categories) {
            layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
            adapter = mCategoriesAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        viewModel.getAllStudiesWords().observe(viewLifecycleOwner, Observer {
            mCategoriesAdapter.setData(it)
        })
    }

}
