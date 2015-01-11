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
