package ro.dobrescuandrei.demonewlibs.restaurant.details

import android.os.Bundle
import ro.andreidobrescu.declarativeadapterkt.DeclarativeAdapter
import ro.dobrescuandrei.demonewlibs.R
import ro.dobrescuandrei.demonewlibs.model.Restaurant
import ro.dobrescuandrei.demonewlibs.model.utils.FirstPageStickyHeader
import ro.dobrescuandrei.demonewlibs.model.utils.SecondPageStickyHeader
import ro.dobrescuandrei.demonewlibs.restaurant.details.headers.FirstPageStickyHeaderView
import ro.dobrescuandrei.demonewlibs.restaurant.details.headers.SecondPageStickyHeaderView
import ro.dobrescuandrei.demonewlibs.restaurant.list.cells.RestaurantCellView
import ro.dobrescuandrei.mvvm.details.BaseDetailsActivity
import ro.dobrescuandrei.mvvm.list.item_decoration.StickyHeaderCustomView
import ro.dobrescuandrei.utils.setupBackIcon

class RestaurantDetailsActivity : BaseDetailsActivity<Restaurant, RestaurantDetailsViewModel, DeclarativeAdapter>()
{
    override fun viewModelClass() : Class<RestaurantDetailsViewModel> = RestaurantDetailsViewModel::class.java
    override fun layout() : Int = R.layout.activity_restaurant_details

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        toolbar.setupBackIcon()
        toolbar.setTitle(R.string.restaurant)
    }

    override fun provideAdapter() : DeclarativeAdapter
    {
        val adapter=DeclarativeAdapter()

        adapter.whenInstanceOf(Restaurant::class,
                use = { RestaurantCellView(it) })
            .whenInstanceOf(
                FirstPageStickyHeader::class,
                use = { FirstPageStickyHeaderView(it) })
            .whenInstanceOf(
                SecondPageStickyHeader::class,
                use = { SecondPageStickyHeaderView(it) })

        return adapter
    }

    override fun provideStickyHeaderModelClass(position: Int): Class<*>? = when
    {
        position>=viewModel.secondPageStickyHeaderIndex -> SecondPageStickyHeader::class.java
        position>=viewModel.firstPageStickyHeaderIndex  -> FirstPageStickyHeader::class.java
        else -> null
    }

    override fun provideStickyHeaderView(position: Int): StickyHeaderCustomView<*>? = when
    {
        position>=viewModel.secondPageStickyHeaderIndex -> SecondPageStickyHeaderView(this)
        position>=viewModel.firstPageStickyHeaderIndex  -> FirstPageStickyHeaderView(this)
        else -> null
    }
}