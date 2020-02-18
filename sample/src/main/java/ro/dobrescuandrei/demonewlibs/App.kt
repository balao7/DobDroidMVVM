package ro.dobrescuandrei.demonewlibs

import android.app.Application
import android.content.Context
import com.chibatching.kotpref.Kotpref
import com.franmontiel.localechanger.LocaleChanger
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import ro.dobrescuandrei.demonewlibs.model.utils.LANGUAGE_ENGLISH
import ro.dobrescuandrei.demonewlibs.model.utils.LANGUAGE_ROMANIAN
import java.util.*

class App : Application()
{
    companion object
    {
        @JvmStatic
        lateinit var context : Context
    }

    override fun onCreate()
    {
        super.onCreate()

        context=this

        Kotpref.init(context = this)

        val locales = LinkedList<Locale>()
        locales.add(Locale(LANGUAGE_ENGLISH))
        locales.add(Locale(LANGUAGE_ROMANIAN))
        LocaleChanger.initialize(applicationContext, locales)

        RxJavaPlugins.setErrorHandler { ex ->
            if (BuildConfig.DEBUG)
                ex.printStackTrace()
        }
    }
}