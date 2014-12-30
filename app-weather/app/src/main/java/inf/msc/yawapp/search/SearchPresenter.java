package inf.msc.yawapp.search;

public interface SearchPresenter {
    public void updateSearch(final String query);

    public void submitSearch(final String query);

    public void close();
}
