package com.carebearlover.learnenglish.ui.screens.category_list_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.db_tables.CategoriesEntity
import com.carebearlover.learnenglish.data.repositories.Repository

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun getAllStudiesWords(): LiveData<List<CategoriesEntity>> = repository.getAllCategories()

}
