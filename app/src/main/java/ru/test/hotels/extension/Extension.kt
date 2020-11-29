package ru.test.hotels.extension

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.test.hotels.BuildConfig
import ru.test.hotels.entity.Hotel
import ru.test.hotels.entity.HotelDto

fun Hotel.toHotelDto(): HotelDto =
    HotelDto(
        id,
        name,
        address,
        stars,
        suitesAvailability.split(":"),
        distance,
        BuildConfig.BASE_URL + imageUrl,
        lat,
        lon
    )

fun Fragment.showSnackMessage(message: String, retryMessage: String, action: () -> Unit) {
    view?.showSnackMessage(message, retryMessage, action)
}

fun View.showSnackMessage(message: String, retryMessage: String, action: () -> Unit) {
    Snackbar
        .make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(retryMessage) { action.invoke() }
        .show()
}
