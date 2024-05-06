package com.example.topredditpost.di

import androidx.lifecycle.ViewModel
import com.example.topredditpost.presentation.viewModel.ShowImageViewModel
import com.example.topredditpost.presentation.viewModel.TopPostViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @ViewModelKey(TopPostViewModel::class)
    @IntoMap
    fun bindsTopPostViewModel(viewModel: TopPostViewModel) : ViewModel

    @Binds
    @ViewModelKey(ShowImageViewModel::class)
    @IntoMap
    fun bindsShowImageViewModel(viewModel: ShowImageViewModel) : ViewModel

}