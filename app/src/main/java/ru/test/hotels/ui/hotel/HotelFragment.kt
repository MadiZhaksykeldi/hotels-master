package ru.test.hotels.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_hotel.*
import ru.test.hotels.entity.HotelDto
import ru.test.hotels.presentation.hotel.HotelPresenter
import ru.test.hotels.presentation.hotel.HotelView
import android.graphics.Bitmap

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_hotel.progressBar
import ru.test.hotels.R
import ru.test.hotels.extension.showSnackMessage

class HotelFragment : MvpAppCompatFragment(), HotelView {

    companion object {
        private const val HOTEL_ID = "HOTEL_ID"

        fun newInstance(id: Long): HotelFragment {
            val bundle = Bundle()
            bundle.putLong(HOTEL_ID, id)

            val fragment = HotelFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    @InjectPresenter
    lateinit var presenter: HotelPresenter

    @ProvidePresenter
    fun createPresenter() = HotelPresenter(arguments!!.getLong(HOTEL_ID))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_hotel, container, false)

    override fun showHotelDetail(hotelDto: HotelDto) {
        hotelName.text = hotelDto.name

        starOne.visibility = if (hotelDto.stars > 0) View.VISIBLE else View.INVISIBLE
        starTwo.visibility = if (hotelDto.stars > 0) View.VISIBLE else View.INVISIBLE
        starThree.visibility = if (hotelDto.stars > 0) View.VISIBLE else View.INVISIBLE
        starFour.visibility = if (hotelDto.stars > 0) View.VISIBLE else View.INVISIBLE
        starFive.visibility = if (hotelDto.stars > 0) View.VISIBLE else View.INVISIBLE

        hotelCenterDistance.text = getString(R.string.hotel_distance, hotelDto.distance.toInt())

        hotelSuites.text = getString(R.string.hotel_suites, hotelDto.suitesAvailability.size)

        Glide.with(this)
            .asBitmap()
            .load(hotelDto.imageUrl)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val targetBitmap = Bitmap.createBitmap(resource, 1, 1, resource.width - 2, resource.height - 2)
                    hotelImage.setImageBitmap(targetBitmap)
                }
            })
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showNoInternetMessage() {
        showSnackMessage(getString(R.string.no_internet_message), getString(R.string.no_internet_reply_message)) { presenter.getHotelById() }
    }
}