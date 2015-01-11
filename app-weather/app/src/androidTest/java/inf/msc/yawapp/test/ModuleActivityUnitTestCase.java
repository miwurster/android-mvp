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

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.test.ActivityTestCase;
import android.view.ContextThemeWrapper;
import android.view.Window;

import inf.msc.yawapp.R;
import inf.msc.yawapp.common.BaseModuleActivity;

/**
 * Unit test case class for BaseModuleActivities
 * <p/>
 * This class was created due to a bug in the ActivityUnitTestCase class of the android test
 * framework (Issue no. 22737: https://code.google.com/p/android/issues/detail?id=22737).
 * The ActivityUnitTestCase class does not work with activities in sub-packages.
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ModuleActivityUnitTestCase<T extends BaseModuleActivity> extends ActivityTestCase {

    private Class<T> activityClass;
    private boolean isAttached = false;
    private boolean isCreated = false;
    private Application mockApplication;
    private Context activityContext;
    private BaseModuleActivity.GraphCreationStrategy graphCreationStrategy;

    public ModuleActivityUnitTestCase(Class<T> activityClass) {
        this.activityClass = activityClass;
    }

    protected T startActivity(Bundle savedInstanceState) {
        assertFalse("Activity already created", isCreated);

        if (!isAttached) {
            assertNotNull(activityClass);
            setActivity(null);
            T newActivity = null;
            try {
                IBinder token = null;
                if (mockApplication == null) {
                    mockApplication = new MockMainApplication();
                }
                ComponentName componentName = new ComponentName(getInstrumentation().getTargetContext(), activityClass);
                Intent startIntent = new Intent(getInstrumentation().getContext(), activityClass);
                startIntent.setComponent(componentName);
                ActivityInfo info = new ActivityInfo();
                CharSequence title = activityClass.getName();
                MockParent parent = new MockParent();
                newActivity = (T) getInstrumentation().newActivity(
                        activityClass, activityContext, token, mockApplication, startIntent, info,
                        title, parent, null, null);
            } catch (Exception e) {
                assertNotNull(newActivity);
            }
            assertNotNull(newActivity);
            setActivity(newActivity);
            if (graphCreationStrategy != null) {
                newActivity.setGraphCreationStrategy(graphCreationStrategy);
            }
            isAttached = true;
        }

        T result = getActivity();
        if (result != null) {
            getInstrumentation().callActivityOnCreate(getActivity(), savedInstanceState);
            isCreated = true;
        }
        return result;
    }

    @Override
    protected T getActivity() {
        return (T) super.getActivity();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activityContext = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
    }

    @Override
    protected void tearDown() throws Exception {
        setActivity(null);
        scrubClass(ModuleActivityUnitTestCase.class);
        super.tearDown();
    }

    public void setApplication(Application mockApplication) {
        this.mockApplication = mockApplication;
    }

    public void setGraphCreationStrategy(BaseModuleActivity.GraphCreationStrategy graphCreationStrategy) {
        this.graphCreationStrategy = graphCreationStrategy;
    }

    public void setActivityContext(Context activityContext) {
        this.activityContext = activityContext;
    }

    /**
     * Copied from ActivityUnitTestCase
     */
    private static class MockParent extends Activity {

        public int requestedOrientation = 0;
        public Intent startedActivityIntent = null;
        public int startedActivityRequest = -1;
        public boolean finished = false;
        public int finishedActivityRequest = -1;

        @Override
        public void setRequestedOrientation(int requestedOrientation) {
            this.requestedOrientation = requestedOrientation;
        }

        @Override
        public int getRequestedOrientation() {
            return requestedOrientation;
        }

        @Override
        public Window getWindow() {
            return null;
        }

        @Override
        public void startActivityFromChild(Activity child, Intent intent, int requestCode) {
            startedActivityIntent = intent;
            startedActivityRequest = requestCode;
        }

        @Override
        public void finishFromChild(Activity child) {
            finished = true;
        }

        @Override
        public void finishActivityFromChild(Activity child, int requestCode) {
            finished = true;
            finishedActivityRequest = requestCode;
        }
    }
}
