package inf.msc.yawapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.List;

import dagger.ObjectGraph;

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
            actionBarToolbar = (Toolbar) findViewById(R.id.weatherSearchToolbar);
            if (actionBarToolbar != null) {
                setSupportActionBar(actionBarToolbar);
            }
        }
        return actionBarToolbar;
    }

    protected abstract List<Object> getModules();
}
