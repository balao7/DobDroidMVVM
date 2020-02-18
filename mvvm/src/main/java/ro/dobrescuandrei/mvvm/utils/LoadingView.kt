package ro.dobrescuandrei.mvvm.utils

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import ro.dobrescuandrei.mvvm.BaseActivity
import ro.dobrescuandrei.mvvm.R
import java.util.concurrent.atomic.AtomicInteger

object LoadingView
{
    private class Tag
    {
        val numberOfRunningTasks = AtomicInteger()
    }

    @JvmStatic
    fun show(context : BaseActivity<*>)
    {
        val alreadyRunningLoadingView=findLoadingView(context)
        if (alreadyRunningLoadingView!=null)
        {
            val tag=alreadyRunningLoadingView.tag as Tag
            tag.numberOfRunningTasks.incrementAndGet()
        }
        else
        {
            val loadingView=LayoutInflater.from(context).inflate(R.layout.dialog_progress, null)

            val tag=Tag()
            tag.numberOfRunningTasks.set(1)
            loadingView.tag=tag

            val rootLayout=context.findViewById<FrameLayout>(android.R.id.content)!!
            rootLayout.addView(loadingView)

            loadingView.setOnLongClickListener { v ->
                hide(v.context as BaseActivity<*>)
                return@setOnLongClickListener true
            }
        }
    }

    @JvmStatic
    fun hide(context : BaseActivity<*>)
    {
        val loadingView=findLoadingView(context)
        if (loadingView!=null)
        {
            val tag=loadingView.tag as Tag
            tag.numberOfRunningTasks.decrementAndGet()

            if (tag.numberOfRunningTasks.get()<0)
                tag.numberOfRunningTasks.set(0)

            if (tag.numberOfRunningTasks.get()==0)
            {
                val rootLayout=context.findViewById<FrameLayout>(android.R.id.content)!!
                rootLayout.removeView(loadingView)
            }
        }
    }

    @JvmStatic
    fun findLoadingView(context : BaseActivity<*>) : View?
    {
        val rootLayout=context.findViewById<FrameLayout>(android.R.id.content)!!
        for (i in 0 until rootLayout.childCount)
        {
            val view=rootLayout.getChildAt(i)!!
            if (view.tag!=null&&view.tag is Tag)
                return view
        }

        return null
    }
}
