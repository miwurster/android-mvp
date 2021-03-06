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

package inf.msc.yawapp.favourites;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import inf.msc.yawapp.model.Location;

public class FavouritesListViewAdapter extends ArrayAdapter<Location> {
    public static final int LAYOUT_RESOURCE = android.R.layout.simple_list_item_1;

    public FavouritesListViewAdapter(Context context) {
        super(context, LAYOUT_RESOURCE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(LAYOUT_RESOURCE, parent, false);
        }
        Location location = getItem(position);
        TextView textView = (TextView) convertView;
        textView.setText(location.getCity());

        return convertView;
    }
}
