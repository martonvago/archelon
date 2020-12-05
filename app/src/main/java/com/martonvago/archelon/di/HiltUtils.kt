package com.martonvago.archelon.di

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController

/**
 * This allows us to use Hilt dependency injection with navgraph-scoped viewmodels
 * Source: https://gist.github.com/Zhuinden/4eab8d3875e90c1686d12a2272388c9b
 */
inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModels(@IdRes navGraphIdRes: Int) =
    viewModels<T>(
        ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
        factoryProducer = { defaultViewModelProviderFactory }
    )