package ro.dobrescuandrei.mvvm;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public abstract class JBaseFragment<VIEW_MODEL extends BaseViewModel> extends Fragment
{
    private Toolbar toolbar;

    public abstract Class<VIEW_MODEL> viewModelClass();

    public VIEW_MODEL getViewModel()
    {
        return new ViewModelProvider(this).get(viewModelClass());
    }

    public Toolbar getToolbar()
    {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar)
    {
        this.toolbar = toolbar;
    }
}
