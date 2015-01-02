package inf.msc.yawapp.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import inf.msc.yawapp.R;

public class NavDrawerListViewAdapter extends ArrayAdapter<NavDrawerItem> {
    public static final int LAYOUT_RESOURCE = R.layout.nav_drawer_item;

    public NavDrawerListViewAdapter(Context context) {
        super(context, LAYOUT_RESOURCE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(LAYOUT_RESOURCE, parent, false);
        }
        NavDrawerItem item = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.nav_item_title);
        textView.setText(getContext().getResources().getString(item.getTitleResource()));
        ImageView imageView = (ImageView) convertView.findViewById(R.id.nav_item_icon);
        imageView.setImageResource(item.getIconResource());

        return convertView;
    }
}
