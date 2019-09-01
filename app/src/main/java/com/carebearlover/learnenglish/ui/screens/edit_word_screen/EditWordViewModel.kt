package com.carebearlover.learnenglish.ui.screens.edit_word_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.carebearlover.learnenglish.data.entities.MyExampleEntity
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import com.carebearlover.learnenglish.data.repositories.MyRepository

class EditWordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MyRepository(application)

    fun updateWord(word: MyWordsEntity) {
        repository.updateWord(word)
    }

    fun getExamplesByWord(word: String): LiveData<List<MyExampleEntity>> =
            repository.getExamplesByWord(word)

}
