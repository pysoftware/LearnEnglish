package com.magere.learnenglish.ui.screens.repeat_screen

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magere.learnenglish.R
import com.magere.learnenglish.data.entities.WordsEntity
import com.magere.learnenglish.extensions.flipTheCard
import com.magere.learnenglish.extensions.format
import com.magere.learnenglish.extensions.today
import kotlinx.android.synthetic.main.flash_card_layout_back.*
import kotlinx.android.synthetic.main.flash_card_layout_front.*
import kotlinx.android.synthetic.main.repeat_fragment.*
import java.util.*

class RepeatFragment : Fragment(), View.OnClickListener {
    private lateinit var textToSpeech: TextToSpeech

    private var counter = 0

    private var todayWords = mutableListOf<WordsEntity>()

    private lateinit var viewModel: RepeatViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repeat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        textToSpeech = TextToSpeech(view.context, TextToSpeech.OnInitListener {
            val result = textToSpeech.setLanguage(Locale.ENGLISH)
            if(result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.d("Language", "Language is not supported")
        })

        layout_speakWordF.setOnClickListener(this)
        layout_speakWordB.setOnClickListener(this)
        btn_notRememberWord.setOnClickListener(this)
        btn_rememberWord.setOnClickListener(this)
    }

    private fun speak() {
//        textToSpeech.shutdown()
        val word = tv_wordF.text.toString()
        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(RepeatViewModel::class.java)
        viewModel.getNearestDate().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                tv_nearestDate.visibility = VISIBLE
                tv_nearestDate.text = "Ближайшее повторение: ${format(it)}"
            }
            else tv_nearestDate.visibility = GONE
        })

        viewModel.getCountTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
            if (it == 0) {
                rl_repeat.visibility = GONE
                rl_chill.visibility = VISIBLE
            }
            tv_countTodayRepeatWords.text = "Осталось повторить: $it"
            progressBar.max = it
        })

        viewModel.getAllTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
            todayWords.addAll(it)
            updateCard()
        })
    }

    private fun acceptWord() {
        easyFlipView.flipTheCard()
        viewModel.updateWordRepeatDate(
                when (todayWords[counter].group) {
                    1 -> today(1)
                    2 -> today(3)
                    3 -> today(7)
                    4 -> today(14)
                    5 -> today(21)
                    6 -> today(30)
                    else -> today(30)
                },
                todayWords[counter].word,
                todayWords[counter].group!!.plus(1)
        )
        if (counter < todayWords.size) {
            counter++
            updateCard()
        }
    }

    private fun declineWord() {
        easyFlipView.flipTheCard()
        viewModel.updateWordRepeatDate(
                today(),
                todayWords[counter].word,
                1
        )
        if (counter < todayWords.size) {
            counter++
            updateCard()
        }
    }

    private fun updateCard() {
        if (todayWords.size != 0 && counter < todayWords.size) {
//            speak()
            rl_repeat.visibility = VISIBLE
            rl_chill.visibility = GONE

            tv_wordF.text = todayWords[counter].word
            tv_wordB.text = todayWords[counter].word

            tv_wordTranslate.text = todayWords[counter].translate
            tv_wordExample.text = todayWords[counter].example1
            tv_translateWordExample.text = todayWords[counter].translateExample1
            tv_wordExample1.text = todayWords[counter].example2
            tv_translateWordExample1.text = todayWords[counter].translateExample2
        } else {
            rl_repeat.visibility = GONE
            rl_chill.visibility = VISIBLE
        }
    }

    override fun onClick(view: View?) {
        when(view!!.id) {
            R.id.layout_speakWordF-> speak()
            R.id.layout_speakWordB-> speak()
            R.id.btn_rememberWord -> acceptWord()
            R.id.btn_notRememberWord -> declineWord()
        }
    }

    override fun onDestroy() {
        textToSpeech.shutdown()
        textToSpeech.stop()
        super.onDestroy()
    }
}
