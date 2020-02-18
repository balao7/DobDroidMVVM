package ro.dobrescuandrei.demonewlibs.api

import io.reactivex.rxjava3.core.Completable
import ro.dobrescuandrei.demonewlibs.model.Restaurant

class AddRestaurantRequest
(
    val restaurant : Restaurant
) : BaseRequest<Completable>()
{
    override fun execute() = Completable.fromCallable {
        Thread.sleep(1000)
    }
}