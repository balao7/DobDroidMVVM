package ro.dobrescuandrei.demonewlibs.api

import io.reactivex.rxjava3.core.Single
import ro.dobrescuandrei.demonewlibs.model.User

class LoginRequest
(
    val username : String,
    val password : String
) : BaseRequest<Single<User>>()
{
    override fun execute() = Single.fromCallable {
        Thread.sleep(1000)
        return@fromCallable User(name = "asdf")
    }
}