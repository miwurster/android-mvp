package inf.msc.yawapp.favourites;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import inf.msc.yawapp.model.Location;

public class FavouritesListViewAdapter extends ArrayAdapter<Location> {
    public static final int LAYOUT_RESOURCE = android.R.layout.simple_list_item_1;

    public FavouritesListViewAdapter(Context context) {
        super(context, LAYOUT_RESOURCE);
    }

    public FavouritesListViewAdapter(Context context, List<Location> objects) {
        super(context, LAYOUT_RESOURCE, objects);
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
