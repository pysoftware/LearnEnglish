package com.carebearlover.learnenglish.ui.screens.repeat_screen

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carebearlover.learnenglish.R
import com.carebearlover.learnenglish.adapters.ExamplesAdapter
import com.carebearlover.learnenglish.data.entities.MyWordsEntity
import com.carebearlover.learnenglish.extensions.flipTheCard
import com.carebearlover.learnenglish.extensions.today
import kotlinx.android.synthetic.main.flash_card_layout_back.*
import kotlinx.android.synthetic.main.flash_card_layout_back.tv_wordTranslate
import kotlinx.android.synthetic.main.flash_card_layout_front.*
import kotlinx.android.synthetic.main.fragment_repeat.*
import java.util.*

class RepeatFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var textToSpeech: TextToSpeech

    private var counter = 0

    private var todayWords = mutableListOf<MyWordsEntity>()

    private lateinit var viewModel: RepeatViewModel
    private lateinit var mAdapter: ExamplesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repeat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initViewModel()
        initExamplesAdapter()
        initTextToSpeech()

        layout_speakWordF.setOnClickListener(this)
        layout_speakWordB.setOnClickListener(this)
        btn_notRememberWord.setOnClickListener(this)
        btn_rememberWord.setOnClickListener(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(RepeatViewModel::class.java)

        viewModel.getCountTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
            tv_countTodayRepeatWords.text = getString(R.string.necessary_to_repeat, it)
        })

        viewModel.getAllTodayRepeatWords(today()).observe(viewLifecycleOwner, Observer {
                todayWords.clear()
                todayWords.addAll(it)
//                todayWords.shuffle()
            updateCard()
        })
    }

    private fun initExamplesAdapter() {
        mAdapter = ExamplesAdapter(activity!!.application)
        with(rv_examplesB) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(view?.context, RecyclerView.VERTICAL, false)
        }
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(view!!.context, TextToSpeech.OnInitListener {
            val result = textToSpeech.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED)
                Log.d("Language", "Language is not supported")
        })
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.layout_speakWordF -> speak()
            R.id.layout_speakWordB -> speak()
            R.id.btn_rememberWord -> acceptWord()
            R.id.btn_notRememberWord -> declineWord()
        }
    }

    private fun speak() {
//        textToSpeech.shutdown()
        val word = tv_wordF.text.toString()
        textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }


    private fun acceptWord() {
        easyFlipView.flipTheCard()
        viewModel.updateWordRepeatDate(
                toDate = when (todayWords[counter].group) {
                    1 -> today(1)
                    2 -> today(3)
                    3 -> today(7)
                    4 -> today(14)
                    5 -> today(21)
                    6 -> today(30)
                    else -> today(30)
                },
                word = todayWords[counter].word,
                group = todayWords[counter].group!!.plus(1)
        )
        counter++
        if (counter < todayWords.size) {
            updateCard()
            counter--
        } else {
            finishRepeating()
        }
    }

    private fun declineWord() {
        easyFlipView.flipTheCard()
        viewModel.updateWordRepeatDate(word = todayWords[counter].word)
        counter++
        if (counter < todayWords.size) {
            updateCard()
        } else
            counter = 0
    }

    private fun updateCard() {
        viewModel.getExamplesByWord(if (todayWords.size != 0) todayWords[counter].word else "")
                .observe(viewLifecycleOwner, Observer {
                    mAdapter.setData(it)
                })
        tv_wordF.text = todayWords[counter].word
        tv_wordB.text = todayWords[counter].word

        tv_wordTranslate.text = todayWords[counter].translate
    }

    private fun finishRepeating() {
        navController.navigate(R.id.action_repeatFragment_to_adFragment)
    }

    override fun onDestroy() {
        textToSpeech.shutdown()
        textToSpeech.stop()
        super.onDestroy()
    }

}
