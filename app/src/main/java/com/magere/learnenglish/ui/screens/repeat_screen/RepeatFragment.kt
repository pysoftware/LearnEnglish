package com.magere.learnenglish.ui.screens.repeat_screen

import android.os.Bundle
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
import com.magere.learnenglish.extensions.today
import kotlinx.android.synthetic.main.flash_card_layout_back.*
import kotlinx.android.synthetic.main.flash_card_layout_front.*
import kotlinx.android.synthetic.main.repeat_fragment.*

class RepeatFragment : Fragment() {

    private var counter = 0

    private var todayWords = mutableListOf<WordsEntity>()

    private lateinit var viewModel: RepeatViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repeat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RepeatViewModel::class.java)

        viewModel.getCountTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {

            tv_countTodayRepeatWords.text = "Осталось повторить: $it"
            progressBar.max = it
        })

        viewModel.getAllTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
            todayWords.addAll(it)
            updateCard()
        })

        btn_repeatWord.setOnClickListener {
            repeatWord()
        }

        btn_notRememberWord.setOnClickListener {
            declineWord()
        }

        btn_rememberWord.setOnClickListener {
            acceptWord()
        }
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
                today(0),
                todayWords[counter].word,
                1
        )
        counter++
        updateCard()
    }

    private fun repeatWord() {
        viewModel.updateWordRepeatDate(
                todayWords[counter].date!!,
                todayWords[counter].word,
                todayWords[counter].group!!
        )
        updateCard()
        easyFlipView.flipTheCard()
        todayWords.add(todayWords[counter])
    }

    private fun updateCard() {
        if (todayWords.size != 0) {
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
}
