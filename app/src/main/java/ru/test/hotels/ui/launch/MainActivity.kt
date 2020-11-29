package ru.test.hotels.ui.launch

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.test.hotels.App
import ru.test.hotels.R
import ru.test.hotels.presentation.launch.MainPresenter
import ru.test.hotels.presentation.launch.MainView
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val navigator = object : SupportAppNavigator(this, R.id.mainContainer) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        App.DAGGER.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }
}