package ro.dobrescuandrei.mvvm.list.item_decoration;

import android.content.Context;
import android.util.AttributeSet;

import ro.andreidobrescu.declarativeadapterkt.view.CellView;

public abstract class StickyHeaderCustomView<MODEL> extends CellView<MODEL>
{
    public StickyHeaderCustomView(Context context)
    {
        super(context);
    }

    public StickyHeaderCustomView(Context context, int layout)
    {
        super(context, layout);
    }

    public StickyHeaderCustomView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public StickyHeaderCustomView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
}
