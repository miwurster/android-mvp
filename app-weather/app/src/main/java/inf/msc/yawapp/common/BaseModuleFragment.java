/*
 * This source code is part of the research paper
 * "Applying Model-View-Presenter Architecture to the Android Framework with App Prototype"
 *
 * Software Architecture and Management,
 * Herman-Hollerith-Zentrum, Reutlingen University
 *
 * Authors:  Dennis G. Geisse,
 *           Iven John,
 *           Daniel Joos,
 *           Sebastian Kotstein,
 *           Michael Wurster
 *
 * Year:     2015
 */

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
