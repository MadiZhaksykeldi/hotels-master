package ru.test.hotels.model.interactor.hotels

import ru.test.hotels.model.repository.HotelRepository
import javax.inject.Inject

class HotelsListInteractor @Inject constructor (private val repository: HotelRepository) {

    fun getHotelsSingle() =
        repository.getHotels()
}