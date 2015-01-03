package inf.msc.yawapp.common;

import android.app.Fragment;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;
import inf.msc.yawapp.MainApplication;

public abstract class BaseModuleFragment extends Fragment {
    private ObjectGraph moduleGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setModuleGraph(((MainApplication) getActivity().getApplication()).createSubGraph(getModules().toArray()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        moduleGraph = null;
    }

    public void setModuleGraph(ObjectGraph moduleGraph) {
        this.moduleGraph = moduleGraph;
        this.moduleGraph.inject(this);
    }

    protected abstract List<Object> getModules();
}
