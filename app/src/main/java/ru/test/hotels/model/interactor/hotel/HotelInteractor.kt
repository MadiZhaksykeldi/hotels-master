package ru.test.hotels.model.interactor.hotel

import ru.test.hotels.model.repository.HotelRepository
import javax.inject.Inject

class HotelInteractor @Inject constructor(private val repository: HotelRepository) {

    fun getHotelByIdSingle(id: Long) =
        repository.getHotelById(id)
}