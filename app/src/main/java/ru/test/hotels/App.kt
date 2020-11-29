package ru.test.hotels

import android.app.Application
import ru.test.hotels.di.Dagger

class App : Application() {

    companion object {
        lateinit var DAGGER: Dagger
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DAGGER = Dagger()
    }
}