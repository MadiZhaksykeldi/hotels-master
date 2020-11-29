package ru.test.hotels.di.component

import dagger.Component
import ru.test.hotels.di.module.NavigationModule
import ru.test.hotels.di.module.NetworkModule
import ru.test.hotels.presentation.hotel.HotelPresenter
import ru.test.hotels.presentation.hotels.HotelsListPresenter
import ru.test.hotels.presentation.launch.MainPresenter
import ru.test.hotels.ui.launch.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, NavigationModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(presenter: MainPresenter)

    fun inject(presenter: HotelsListPresenter)

    fun inject(presenter: HotelPresenter)

}