package inf.msc.yawapp.search;

import android.content.SearchRecentSuggestionsProvider;

public class SearchHistoryProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "inf.msc.yawapp.search.SearchHistoryProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchHistoryProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
