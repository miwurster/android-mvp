package inf.msc.yawapp.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NavDrawerListViewAdapter extends ArrayAdapter<NavDrawerItem> {
    public static final int LAYOUT_RESOURCE = android.R.layout.simple_list_item_1;

    public NavDrawerListViewAdapter(Context context) {
        super(context, LAYOUT_RESOURCE);
    }

    public NavDrawerListViewAdapter(Context context, List<NavDrawerItem> objects) {
        super(context, LAYOUT_RESOURCE, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(LAYOUT_RESOURCE, parent, false);
        }
        NavDrawerItem item = getItem(position);
        TextView textView = (TextView) convertView;
        textView.setText(getContext().getResources().getString(item.getTitleResource()));

        return convertView;
    }
}
