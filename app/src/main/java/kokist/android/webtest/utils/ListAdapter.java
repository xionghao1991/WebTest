package kokist.android.webtest.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import kokist.android.webtest.R;

/**
 * Created by xiong on 2015/6/10.
 */
public class ListAdapter extends BaseAdapter {

    private int layoutid;
    private Context context;
    private String[] strs;
    private int[] drableids;

    public ListAdapter(Context context, String[] strs, int[] drableids) {
        this.context = context;
        this.strs = strs;
        this.drableids = drableids;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public Object getItem(int position) {
        return strs[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null) {
            viewholder = new Viewholder();
            convertView = View.inflate(context, R.layout.pipei_list_item_layout, null);
            viewholder.list_icon = (ImageView) convertView.findViewById(R.id.list_icon);
            viewholder.list_tilte = (TextView) convertView.findViewById(R.id.list_name);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }
        viewholder.list_tilte.setText(strs[position]);
        viewholder.list_icon.setImageResource(drableids[position]);
        return convertView;
    }
    class Viewholder {
        ImageView list_icon;
        TextView list_tilte;
    }

}

