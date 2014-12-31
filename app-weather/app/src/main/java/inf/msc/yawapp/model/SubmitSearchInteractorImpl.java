package inf.msc.yawapp.model;

import android.content.Intent;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;
import inf.msc.yawapp.common.Intents;

public class SubmitSearchInteractorImpl implements SubmitSearchInteractor {

    @Inject
    MainApplication application;

    @Override
    public void submitSearch(String query) {
        Intent intent = new Intent(Intents.SEARCH_WEATHER);
        intent.putExtra("query", query);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        application.startActivity(intent);
    }
}
