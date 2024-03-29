package com.martonvago.archelon.ui.shared

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

fun View.setNavigateOnClickListener(directions: NavDirections, doBeforeNavigate: () -> Unit = {}) {
    this.setOnClickListener {
        doBeforeNavigate()
        this.findNavController().navigate(directions)
    }
}

fun View.setNavigateOnClickListener(@IdRes destinationIdRes: Int, doBeforeNavigate: () -> Unit = {}) {
    this.setOnClickListener {
        doBeforeNavigate()
        this.findNavController().navigate(destinationIdRes)
    }
}

fun View.setNavigateUpOnClickListener(doBeforeNavigate: () -> Unit = {}) {
    this.setOnClickListener {
        doBeforeNavigate()
        this.findNavController().navigateUp()
    }
}

fun View.setNavigateOnClickListener(@IdRes destinationIdRes: Int, parent: Fragment, doBeforeNavigate: () -> Unit = {}) {
    this.setOnClickListener {
        doBeforeNavigate()
        parent.findNavController().navigate(destinationIdRes)
    }
}

fun View.setNavigateUpOnClickListener(parent: Fragment, doBeforeNavigate: () -> Unit = {}) {
    this.setOnClickListener {
        doBeforeNavigate()
        parent.findNavController().navigateUp()
    }
}

fun View.configureOptionalNavButton(@IdRes navigationActionId: Int?) {
    if (navigationActionId != null) {
        this.setNavigateOnClickListener(navigationActionId)
    } else {
        this.visibility = View.GONE
    }
}