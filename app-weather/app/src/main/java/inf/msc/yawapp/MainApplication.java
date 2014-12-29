package inf.msc.yawapp;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;
import inf.msc.yawapp.model.ModelModule;

public class MainApplication extends Application {

    //public static Application app = null;

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
        //app = this;
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(
                new MainModule(this),
                new ModelModule());
    }

    public ObjectGraph createSubGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
