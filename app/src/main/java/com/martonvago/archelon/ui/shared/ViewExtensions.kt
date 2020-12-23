package com.martonvago.archelon.ui.shared

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.martonvago.archelon.ui.createsurvey.SelectFieldArgs
import com.martonvago.archelon.ui.createsurvey.SelectFieldsAdapter
import com.martonvago.archelon.ui.createsurvey.dialogs.select.SelectOptionArgs
import com.martonvago.archelon.ui.createsurvey.observers.TextInputFieldArgs
import com.martonvago.archelon.ui.createsurvey.observers.TextInputFieldsAdapter

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

fun RecyclerView.setUpSelectAdapter(
    selectFields: List<SelectFieldArgs>,
    lifecycleOwner: LifecycleOwner,
    navActionToSelectDialog: (SelectOptionArgs) -> NavDirections
) {
    this.adapter = SelectFieldsAdapter(selectFields, navActionToSelectDialog, lifecycleOwner)
}

fun RecyclerView.setUpTextInputAdapter(
    textInputFields: List<TextInputFieldArgs>,
    lifecycleOwner: LifecycleOwner,
) {
    this.adapter = TextInputFieldsAdapter(textInputFields, lifecycleOwner)
}