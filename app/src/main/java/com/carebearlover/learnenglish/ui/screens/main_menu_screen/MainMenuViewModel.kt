package com.carebearlover.learnenglish.ui.screens.main_menu_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.repositories.MyRepository

class MainMenuViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MyRepository(application)

    fun getNearestDate(): LiveData<Long> = repository.getNearestDate()

    fun getCountTodayRepeatWords(date: Long): LiveData<Int> = repository.getCountTodayRepeatWords(date)
}
