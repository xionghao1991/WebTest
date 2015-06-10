package kokist.android.webtest.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import kokist.android.webtest.PiPeiActivity;
import kokist.android.webtest.R;

/**
 * Created by xiong on 2015/6/10.
 */
public class PopListAdapter extends BaseAdapter {
    private String[] strs;
    private Context context;
    private  boolean flag;
    private int checked_postion=0;
    public PopListAdapter(Context context,String[] strs,boolean flag){
        this.strs=strs;
        this.context=context;
        this.flag=flag;
   }

    public int getChecked_postion() {
        return checked_postion;
    }

    public void setChecked_postion(int checked_postion) {
        this.checked_postion = checked_postion;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
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
            convertView = View.inflate(context, R.layout.list_item_only_textview, null);
            viewholder.list_tilte = (TextView) convertView.findViewById(R.id.list_name);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

            viewholder.list_tilte.setText(strs[position]);


        if (flag) {
            viewholder.list_tilte.setBackgroundColor(context.getResources().getColor(R.color.lightgary));
        }
        if (position==checked_postion){
            if (flag) {
                viewholder.list_tilte.setTextColor(context.getResources().getColor(R.color.orgen));
            }else {
                viewholder.list_tilte.setBackgroundColor(Color.LTGRAY);
            }

        }else {
            if (flag) {
                viewholder.list_tilte.setTextColor(context.getResources().getColor(R.color.text_normal_gray));
            }else {
                viewholder.list_tilte.setBackgroundColor(Color.WHITE);
            }
        }
        return convertView;
    }

    class Viewholder {
        TextView list_tilte;
    }
}

