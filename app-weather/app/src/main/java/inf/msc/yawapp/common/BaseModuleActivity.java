package inf.msc.yawapp.common;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import dagger.ObjectGraph;
import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.R;

public abstract class BaseModuleActivity extends ActionBarActivity {
    private ObjectGraph moduleGraph;
    private Toolbar actionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moduleGraph = ((MainApplication) getApplication()).createSubGraph(getModules().toArray());
        moduleGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moduleGraph = null;
    }

    protected Toolbar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (actionBarToolbar != null) {
                setSupportActionBar(actionBarToolbar);
            }
        }
        return actionBarToolbar;
    }

    protected abstract List<Object> getModules();
}
