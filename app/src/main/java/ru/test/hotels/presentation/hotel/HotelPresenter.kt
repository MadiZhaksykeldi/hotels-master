package ru.test.hotels.presentation.hotel

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router
import ru.test.hotels.App
import ru.test.hotels.model.interactor.hotel.HotelInteractor
import javax.inject.Inject

@InjectViewState
class HotelPresenter(private val id: Long) : MvpPresenter<HotelView>() {

    @Inject
    lateinit var interactor: HotelInteractor

    @Inject
    lateinit var router: Router

    private val compositeDisposable = CompositeDisposable()

    init {
        App.DAGGER.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        compositeDisposable.add(getHotelById())
    }

    fun getHotelById(): Disposable =
        interactor.getHotelByIdSingle(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe({ data -> viewState.showHotelDetail(data) },
                { viewState.showNoInternetMessage() })
}