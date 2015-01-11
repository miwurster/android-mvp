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

package inf.msc.yawapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import inf.msc.yawapp.MainApplication;

public class CitySearchSuggestions implements SearchSuggestions {

    private MainApplication application;
    private List<String> knownCities;

    @Inject
    public CitySearchSuggestions(MainApplication application) {
        this.application = application;
        load();
    }

    private void load() {
        Set<String> deDupCities = new HashSet<>();
        try (InputStream is = application.getAssets().open("data/city_list.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    deDupCities.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        knownCities = new ArrayList<>(deDupCities);
        Collections.sort(knownCities);
    }

    @Override
    public List<String> getSuggestions(String search, int limit) {
        if (limit == 0) {
            limit = -1;
        }
        String lsearch = search.toLowerCase();
        List<String> result = new ArrayList<>();
        int count = 0;
        for (final String city : knownCities) {
            if (city.toLowerCase().startsWith(lsearch)) {
                result.add(city);
                ++count;
                if (count == limit) {
                    break;
                }
            }
        }
        return result;
    }
}
