package com.example.topredditpost.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component
interface ApplicationComponent {

    @Component.Factory
    interface Factory{
        fun create(
          @BindsInstance application: Application
        ) : ApplicationComponent
    }

}