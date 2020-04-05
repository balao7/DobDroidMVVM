package ro.dobrescuandrei.mvvm.editor

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ro.dobrescuandrei.mvvm.BaseViewModel
import ro.dobrescuandrei.mvvm.R
import ro.dobrescuandrei.mvvm.utils.NonNullableLiveData
import ro.dobrescuandrei.mvvm.utils.notify

abstract class BaseEditorViewModel<MODEL : Any> : BaseViewModel
{
    @PublishedApi
    internal val modelLiveData : NonNullableLiveData<MODEL>

    constructor(model : MODEL)
    {
        this.modelLiveData=NonNullableLiveData(initialValue = model)
    }

    @JvmField
    @PublishedApi
    internal var shouldNotifyModelLiveDataOnPropertyChange : Boolean = true

    @JvmField
    @PublishedApi
    internal var _addMode : Boolean = true

    var isValid : Boolean = false

    open val  addMode get() = _addMode
    open val editMode get() = !_addMode

    abstract fun add (model : MODEL) : Completable
    abstract fun edit(model : MODEL) : Completable

    override fun onCreate()
    {
        super.onCreate()

        onCreate(modelLiveData.value)
    }

    open fun onCreate(model : MODEL) {}

    fun notifyChange(consumer : (MODEL) -> (Unit))
    {
        consumer(modelLiveData.value)

        if (shouldNotifyModelLiveDataOnPropertyChange)
        {
            modelLiveData.notify()
        }
    }

    @SuppressLint("CheckResult")
    fun onSaveButtonClicked()
    {
        if (isValid)
        {
            showLoading()

            (if (addMode)
                add(modelLiveData.value)
            else edit(modelLiveData.value))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = { exception ->
                    hideLoading()

                    showError(exception)
                }, onComplete = {
                    hideLoading()

                    if (addMode)
                        onAdded(modelLiveData.value)
                    else onEdited(modelLiveData.value)
                })
        }
        else
        {
            showError(R.string.you_have_errors_please_correct)
        }
    }

    open fun onAdded(model : MODEL) {}
    open fun onEdited(model : MODEL) {}
}
