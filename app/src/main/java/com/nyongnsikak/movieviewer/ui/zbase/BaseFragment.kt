package com.nyongnsikak.movieviewer.ui.zbase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment(): Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        observeData()

    }


    fun navigateUp() {
        requireActivity().onBackPressed()
    }

    fun launchFragment(resId: Int) {
        findNavController().navigate(resId)
    }

    fun launchFragment(action: NavDirections) {
        findNavController().navigate(action)
    }


    open fun setClickListeners() {}

    open fun observeData() {}

}