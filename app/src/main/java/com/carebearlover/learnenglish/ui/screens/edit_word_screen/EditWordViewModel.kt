package com.carebearlover.learnenglish.ui.screens.edit_word_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.ExampleEntity
import com.carebearlover.learnenglish.data.entities.WordsEntity
import com.carebearlover.learnenglish.data.repositories.Repository

class EditWordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun updateWord(word: WordsEntity) {
        repository.updateWord(word)
    }

    fun getExamplesByWord(word: String): LiveData<List<ExampleEntity>> =
            repository.getExamplesByWord(word)

}
