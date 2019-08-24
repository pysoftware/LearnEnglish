package com.magere.learnenglish.ui.screens.edit_word_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.magere.learnenglish.data.entities.ExampleEntity
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.data.repositories.Repository

class EditWordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun updateWord(word: WordsEntity) {
        repository.updateWord(word)
    }

    fun getExamplesByWord(word: String): LiveData<List<ExampleEntity>> =
            repository.getExamplesByWord(word)

}
