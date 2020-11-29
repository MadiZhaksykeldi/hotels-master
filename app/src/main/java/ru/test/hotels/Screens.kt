package ru.test.hotels

import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.test.hotels.ui.hotel.HotelFragment
import ru.test.hotels.ui.hotels.HotelsListFragment

class Screens {

    object HotelsScreen : SupportAppScreen() {
        override fun getFragment() = HotelsListFragment()
    }

    data class HotelScreen(val id: Long) : SupportAppScreen() {
        override fun getFragment() = HotelFragment.newInstance(id)
    }
}