package ru.test.hotels.presentation.launch

import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.test.hotels.App
import ru.test.hotels.Screens
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    init {
        App.DAGGER.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        router.newRootScreen(Screens.HotelsScreen)
    }
}