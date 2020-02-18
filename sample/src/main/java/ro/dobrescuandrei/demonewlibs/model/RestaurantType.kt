package ro.dobrescuandrei.demonewlibs.model

import android.content.res.Resources
import ro.dobrescuandrei.demonewlibs.App
import ro.dobrescuandrei.demonewlibs.R

enum class RestaurantType
(
    val code : Int
)
{
    Normal(1),
    FastFood(2);

    override fun toString() = toString(App.context.resources)

    fun toString(resources : Resources) = when(this)
    {
        Normal -> resources.getString(R.string.normal)
        FastFood -> resources.getString(R.string.fast_food)
    }
}
