package inf.msc.yawapp.search;

import javax.inject.Inject;

public class SearchPresenterImpl implements SearchPresenter {
    @Inject
    SearchView view;

    @Inject
    SubmitSearchInteractor submitSearchInteractor;

    @Override
    public void updateSearch(String query) {

    }

    @Override
    public void submitSearch(String query) {
        submitSearchInteractor.submitSearch(query);
        view.close();
    }

    @Override
    public void close() {
        view.close();
    }
}
