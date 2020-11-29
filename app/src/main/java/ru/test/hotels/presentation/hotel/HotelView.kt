package ru.test.hotels.presentation.hotel

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.test.hotels.entity.HotelDto

@StateStrategyType(AddToEndSingleStrategy::class)
interface HotelView : MvpView {

    fun showHotelDetail(hotelDto: HotelDto)

    @StateStrategyType(SkipStrategy::class)
    fun showNoInternetMessage()

    fun showProgress()

    fun hideProgress()
}