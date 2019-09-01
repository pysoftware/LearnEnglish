package com.carebearlover.learnenglish.ui.screens.main_menu_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.extensions.format
import com.carebearlover.learnenglish.extensions.today
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment() {

    private lateinit var viewModel: MainMenuViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViewModel()

        btn_start_repeat.setOnClickListener {
            navController.navigate(R.id.action_mainMenuFragment_to_repeatFragment)
        }

        fb_menu_add_word.setOnClickListener {
            navController.navigate(R.id.action_mainMenuFragment_to_addWordFragment)
        }
    }
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainMenuViewModel::class.java)

        viewModel.getNearestDate().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                tv_nearestDate.visibility = VISIBLE
                tv_nearestDate.text = getString(R.string.nearest_repeat, format(it))
            } else tv_nearestDate.visibility = GONE
        })

        viewModel.getCountTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
            if (it <= 0) {
                ll_vacation.visibility = VISIBLE
                ll_repeat.visibility = GONE
            } else {
                ll_vacation.visibility = GONE
                ll_repeat.visibility = VISIBLE
            }
            tv_todayCountRepeatWords.text = getString(R.string.necessary_to_repeat, it)
        })


    }
}
