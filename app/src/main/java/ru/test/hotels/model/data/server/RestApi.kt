package ru.test.hotels.model.data.server

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.test.hotels.entity.Hotel

interface RestApi {

    companion object {
        const val MAX_CONNECTION_TIMEOUT: Long = 15
        const val MAX_READ_TIMEOUT: Long = 15
    }

    @GET("0777.json")
    fun getHotels(): Single<List<Hotel>>

    @GET("{id}.json")
    fun getHotelById(@Path("id") id: Long): Single<Hotel>
}