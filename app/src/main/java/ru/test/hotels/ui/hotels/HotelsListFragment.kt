package ru.test.hotels.ui.hotels

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_hotels.*
import ru.test.hotels.R
import ru.test.hotels.entity.HotelDto
import ru.test.hotels.extension.showSnackMessage
import ru.test.hotels.presentation.hotels.HotelsListPresenter
import ru.test.hotels.presentation.hotels.HotelsListView

class HotelsListFragment : MvpAppCompatFragment(), HotelsListView {

    @InjectPresenter
    lateinit var presenter: HotelsListPresenter

    private val groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_hotels, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hotelsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, _ -> presenter.onHotelClick((item as HotelListItem).hotel.id) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_hotel_sort, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.sort_distance -> {
            presenter.filterHotels(HotelsListPresenter.HotelSort.DISTANCE)
            presenter.attachView(this)
            true
        }
        R.id.sort_suites -> {
            presenter.filterHotels(HotelsListPresenter.HotelSort.SUITES)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showNoInternetMessage() {
        showSnackMessage(getString(R.string.no_internet_message), getString(R.string.no_internet_reply_message)) { presenter.getHotels() }
    }

    override fun showHotels(list: List<HotelDto>) {
        val items = list.map { HotelListItem(it) }
        groupAdapter.update(items)
    }
}