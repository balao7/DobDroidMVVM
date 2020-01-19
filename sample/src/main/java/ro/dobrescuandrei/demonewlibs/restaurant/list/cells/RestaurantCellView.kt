package ro.dobrescuandrei.demonewlibs.restaurant.list.cells

import android.content.Context
import android.util.AttributeSet
import kotlinx.android.synthetic.main.cell_restaurant.view.*
import ro.andreidobrescu.declarativeadapterkt.model.ModelBinder
import ro.dobrescuandrei.demonewlibs.R
import ro.dobrescuandrei.demonewlibs.model.Restaurant
import ro.dobrescuandrei.demonewlibs.restaurant.list.RestaurantListActivity
import ro.dobrescuandrei.demonewlibs.router.ActivityRouter
import ro.dobrescuandrei.mvvm.chooser.ChooserCellView

class RestaurantCellView : ChooserCellView<Restaurant>
{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layout() : Int = R.layout.cell_restaurant

    @ModelBinder
    fun setRestaurant(restaurant : Restaurant)
    {
        nameLabel.text=restaurant.name

        nameLabel.setOnCellClickListener(withModel = restaurant) {
            if (it.context is RestaurantListActivity)
                ActivityRouter.startRestaurantDetailsActivity(from = it.context, restaurant = restaurant)
            else ActivityRouter.startEditRestaurantActivity(from = it.context, restaurant = restaurant)
        }
    }
}