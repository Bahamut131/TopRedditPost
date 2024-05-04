package com.example.topredditpost

import android.app.Application
import com.example.topredditpost.di.ApplicationComponent
import com.example.topredditpost.di.DaggerApplicationComponent

class TopPostApp : Application(){

    val component : ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}