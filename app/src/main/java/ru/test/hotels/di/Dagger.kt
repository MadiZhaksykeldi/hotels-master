package ru.test.hotels.di

import ru.test.hotels.di.component.AppComponent
import ru.test.hotels.di.component.DaggerAppComponent

class Dagger {

    val appComponent: AppComponent = DaggerAppComponent.builder().build()
}