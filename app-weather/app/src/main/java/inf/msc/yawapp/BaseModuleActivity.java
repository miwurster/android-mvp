package inf.msc.yawapp;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;

public abstract class BaseModuleActivity extends Activity {
    private ObjectGraph moduleGraph;

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

    protected abstract List<Object> getModules();
}
