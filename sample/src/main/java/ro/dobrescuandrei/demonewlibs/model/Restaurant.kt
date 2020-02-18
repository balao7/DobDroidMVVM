package ro.dobrescuandrei.demonewlibs.model

import ro.dobrescuandrei.demonewlibs.model.utils.ID
import ro.dobrescuandrei.demonewlibs.model.utils.uuid
import java.io.Serializable

open class Restaurant
(
    var id : ID = uuid(),
    var name : String = "",
    var rating : Int = 3,
    var type : RestaurantType? = null
) : Serializable
