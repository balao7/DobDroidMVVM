package ro.dobrescuandrei.mvvm.details

import io.reactivex.rxjava3.core.Single
import ro.dobrescuandrei.mvvm.list.BaseListViewModel
import ro.dobrescuandrei.mvvm.utils.DummyFilter

abstract class BaseDetailsViewModel<MODEL : Any> : BaseListViewModel<Any, DummyFilter>(DummyFilter())
{
    lateinit var model : MODEL

    abstract fun getItems() : Single<List<Any>>
    override fun getItems(filter: DummyFilter): Single<List<Any>> = getItems()
}