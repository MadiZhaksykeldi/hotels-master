package ru.test.hotels.model.repository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.test.hotels.entity.Hotel
import ru.test.hotels.entity.HotelDto
import ru.test.hotels.extension.toHotelDto
import ru.test.hotels.model.data.server.RestApi
import javax.inject.Inject

class HotelRepository @Inject constructor(private val restApi: RestApi) {

    private var cacheHotels: MutableList<HotelDto> = mutableListOf()

    fun getHotels() =
        restApi.getHotels()
            .flattenAsObservable { it }
            .map { hotel: Hotel -> hotel.toHotelDto() }
            .toList()
            .doOnSuccess { t -> cacheHotels.addAll(t) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getHotelById(id: Long) =
        restApi.getHotelById(id)
            .map { hotel: Hotel -> hotel.toHotelDto() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}