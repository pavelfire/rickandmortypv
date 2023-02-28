package com.vk.directop.rickandmortypv.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vk.directop.rickandmortypv.MainActivity
import com.vk.directop.rickandmortypv.R

class SplashFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Thread {
            handler.postDelayed({
                (activity as MainActivity).moveToNext()
            }, DELAY)
        }.start()


    }

    companion object {
        @JvmStatic
        private val DELAY = 1500L // milliseconds
    }
}
