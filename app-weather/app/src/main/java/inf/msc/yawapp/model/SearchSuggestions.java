package inf.msc.yawapp.model;

import java.util.List;

public interface SearchSuggestions {
    public List<String> getSuggestions(final String search, int limit);
}
