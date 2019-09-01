package com.carebearlover.learnenglish.ui.screens.ads_screen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.carebearlover.learnenglish.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_ads.*

class AdsFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ads, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        MobileAds.initialize(context)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        btn_skip_ad.setOnClickListener {
            navController.navigate(R.id.action_adFragment_to_mainMenuFragment)
        }
    }


}
