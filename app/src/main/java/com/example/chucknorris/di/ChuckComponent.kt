package com.example.chucknorris.di

import com.example.chucknorris.MainActivity
import dagger.Component

@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ChuckComponent {
    fun inject(mainActivity: MainActivity)
}