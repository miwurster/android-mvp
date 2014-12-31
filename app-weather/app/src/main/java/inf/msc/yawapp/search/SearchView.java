package inf.msc.yawapp.search;

import java.util.List;

public interface SearchView {
    public void close();

    public void clearSuggestions();

    public void showSuggestions(final List<String> suggestions);
}
