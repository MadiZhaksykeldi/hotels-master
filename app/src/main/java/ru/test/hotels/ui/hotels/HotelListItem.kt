package ru.test.hotels.ui.hotels

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import ru.test.hotels.R
import ru.test.hotels.entity.HotelDto

class HotelListItem(val hotel: HotelDto) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.hotelName).text = hotel.name

        viewHolder.itemView.findViewById<ImageView>(R.id.starOne).visibility =
            if (hotel.stars > 0) View.VISIBLE else View.INVISIBLE
        viewHolder.itemView.findViewById<ImageView>(R.id.starTwo).visibility =
            if (hotel.stars > 1) View.VISIBLE else View.INVISIBLE
        viewHolder.itemView.findViewById<ImageView>(R.id.starThree).visibility =
            if (hotel.stars > 2) View.VISIBLE else View.INVISIBLE
        viewHolder.itemView.findViewById<ImageView>(R.id.starFour).visibility =
            if (hotel.stars > 3) View.VISIBLE else View.INVISIBLE
        viewHolder.itemView.findViewById<ImageView>(R.id.starFive).visibility =
            if (hotel.stars > 4) View.VISIBLE else View.INVISIBLE

        viewHolder.itemView.findViewById<TextView>(R.id.hotelCenterDistance).text =
            viewHolder.itemView.resources.getString(R.string.hotel_distance, hotel.distance.toInt())

        viewHolder.itemView.findViewById<TextView>(R.id.hotelSuites).text =
            viewHolder.itemView.resources.getString(R.string.hotel_suites, hotel.suitesAvailability.size)
    }

    override fun getLayout(): Int {
        return R.layout.i_hotels_list
    }
}