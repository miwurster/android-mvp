package inf.msc.yawapp.test;

import dagger.ObjectGraph;
import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.R;

public class MockMainApplication extends MainApplication {
    @Override
    public void onCreate() {
        setTheme(R.style.AppTheme);
    }

    @Override
    public ObjectGraph createSubGraph(Object... modules) {
        return ObjectGraph.create(modules);
    }
}
