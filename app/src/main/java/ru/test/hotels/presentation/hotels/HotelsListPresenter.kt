package ru.test.hotels.presentation.hotels

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router
import ru.test.hotels.App
import ru.test.hotels.Screens
import ru.test.hotels.entity.HotelDto
import ru.test.hotels.model.interactor.hotels.HotelsListInteractor
import javax.inject.Inject

@InjectViewState
class HotelsListPresenter : MvpPresenter<HotelsListView>() {

    @Inject
    lateinit var interactor: HotelsListInteractor

    @Inject
    lateinit var router: Router

    private val compositeDisposable = CompositeDisposable()

    private var hotels: MutableList<HotelDto> = mutableListOf()

    enum class HotelSort {
        DISTANCE,
        SUITES
    }

    init {
        App.DAGGER.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        compositeDisposable.add(getHotels())
    }

    fun getHotels(): Disposable =
        interactor.getHotelsSingle()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe(
                { data ->
                    hotels.addAll(data)
                    viewState.showHotels(data)
                }
            ) { viewState.showNoInternetMessage() }

    fun filterHotels(sort: HotelSort) {
        when (sort) {
            HotelSort.DISTANCE -> hotels.sortBy { hotelDto -> hotelDto.distance }
            HotelSort.SUITES -> hotels.sortBy { hotelDto -> hotelDto.suitesAvailability.size }
        }
        viewState.showHotels(hotels)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    fun onHotelClick(id: Long) {
        router.navigateTo(Screens.HotelScreen(id))
    }
}