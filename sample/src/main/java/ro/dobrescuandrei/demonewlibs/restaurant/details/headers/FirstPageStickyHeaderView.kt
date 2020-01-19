package ro.dobrescuandrei.demonewlibs.restaurant.details.headers

import android.content.Context
import android.util.AttributeSet
import ro.andreidobrescu.declarativeadapterkt.model.ModelBinder
import ro.dobrescuandrei.demonewlibs.R
import ro.dobrescuandrei.demonewlibs.model.utils.FirstPageStickyHeader
import ro.dobrescuandrei.mvvm.list.item_decoration.StickyHeaderCustomView


class FirstPageStickyHeaderView : StickyHeaderCustomView<FirstPageStickyHeader>
{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layout() = R.layout.header_first_page

    @ModelBinder
    fun setHeaderData(header : FirstPageStickyHeader) {}
}