package com.example.topredditpost.di

import android.app.Application
import com.example.topredditpost.presentation.activity.MainActivity
import com.example.topredditpost.presentation.fragment.ShowImageFragment
import com.example.topredditpost.presentation.fragment.TopPostFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject

@ApplicationScope
@Component(modules = [DataModule::class,ViewModelModule::class])
interface ApplicationComponent {


    fun inject(activity : MainActivity)

    fun inject(fragment : TopPostFragment)

    fun inject(fragment: ShowImageFragment)

    @Component.Factory
    interface Factory{
        fun create(
          @BindsInstance application: Application
        ) : ApplicationComponent
    }

}