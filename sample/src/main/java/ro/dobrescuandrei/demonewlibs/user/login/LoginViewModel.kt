package ro.dobrescuandrei.demonewlibs.user.login

import android.text.TextUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxkotlin3.subscribeBy
import ro.dobrescuandrei.demonewlibs.R
import ro.dobrescuandrei.demonewlibs.api.LoginRequest
import ro.dobrescuandrei.demonewlibs.model.utils.OnLoggedInEvent
import ro.dobrescuandrei.demonewlibs.model.utils.Preferences
import ro.dobrescuandrei.mvvm.BaseViewModel
import ro.dobrescuandrei.mvvm.eventbus.ForegroundEventBus

class LoginViewModel : BaseViewModel()
{
    fun onLoginClicked(username : String, password : String)
    {
        when
        {
            TextUtils.isEmpty(username) -> showError(R.string.please_type_username)
            TextUtils.isEmpty(password) -> showError(R.string.please_type_password)
            else ->
            {
                showLoading()

                LoginRequest(username, password).execute()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onSuccess = { user ->
                        hideLoading()

                        Preferences.userId=user.id
                        Preferences.username=user.name

                        ForegroundEventBus.post(OnLoggedInEvent())
                    }, onError = {
                        hideLoading()

                        showError(R.string.invalid_username_or_password)
                    })
            }
        }
    }
}