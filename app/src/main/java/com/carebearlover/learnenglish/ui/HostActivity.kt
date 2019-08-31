package com.carebearlover.learnenglish.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.ui.screens.repeat_screen.RepeatViewModel
import kotlinx.android.synthetic.main.activity_host.*

class HostActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: RepeatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottom_nav.setupWithNavController(navController)

        viewModel = ViewModelProviders.of(this).get(RepeatViewModel::class.java)
        viewModel.updateWordRepeatDate()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
