package ro.dobrescuandrei.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

public abstract class JBaseActivity<VIEW_MODEL extends BaseViewModel> extends AppCompatActivity
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
