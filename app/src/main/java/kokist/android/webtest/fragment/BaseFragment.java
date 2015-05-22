package kokist.android.webtest.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kokist.android.webtest.JobActivity;
import kokist.android.webtest.R;
import kokist.android.webtest.model.GridItem;
import kokist.android.webtest.utils.Utils;

/**
 * Created by Administrator on 2015/5/21.
 */
public class BaseFragment extends Fragment {

    private View view;
    private ViewPager viewpager;
    private GridView gridvew;
    private FragmentActivity context;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.index, null);
        initView();
        return view;
    }

    private List<Fragment> list;
    private List<GridItem> gridlist;
    private LinearLayout lineview;
    private void initView() {
        list = new ArrayList<Fragment>();
        initgridlist();
        viewpager = (ViewPager) view.findViewById(R.id.headerviewpager);
        list.add(new ImageFragment(1));
        list.add(new ImageFragment(2));
        list.add(new ImageFragment(3));
        gridvew = (GridView) view.findViewById(R.id.gridview);
        lineview=(LinearLayout)view.findViewById(R.id.point_layout);

        viewpager.setPageTransformer(true, new pagertrans());
        viewpager.setAdapter(new imgadapter(getFragmentManager()));
        initrollview();
        gridvew.setAdapter(new gridadatper());
        gridvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it;
             switch (position){
                 case 0:
                     it=new Intent(context, JobActivity.class);
                     it.putExtra("flag",position);
                     context.startActivity(it);
                     break;
                 case 1:
                     it=new Intent(context, JobActivity.class);
                     it.putExtra("flag",position);
                     context.startActivity(it);
                     break;
                 case 2:
                     break;
                 case 3:
                     break;
                 case 4:
                     break;
                 case 5:
                     break;
                 case 6:
                     break;
                 case 7:
                     break;
                 case 8:
                     break;

             }
            }
        });
        initanmiation();
    }

    private void initrollview() {
        initdot(3);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int oldpostion=0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                 if (lineview.getChildCount()>0){
                     lineview.getChildAt(position).setBackgroundResource(R.color.orgen);
                     lineview.getChildAt(oldpostion).setBackgroundResource(R.color.whlite);
                 }
                oldpostion=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initdot(int i) {
        lineview.removeAllViews();
        for (int j = 0; j < i; j++) {
            View view=new View(context);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(Utils.dpToPx(5),Utils.dpToPx(5));
            params.setMargins(Utils.dpToPx(5),Utils.dpToPx(5),Utils.dpToPx(5),Utils.dpToPx(5));
            view.setLayoutParams(params);
            if (j==0){
                view.setBackgroundResource(R.color.orgen);
            }else {
                view.setBackgroundResource(R.color.whlite);
            }
            lineview.addView(view);
        }


    }

    private void initanmiation() {


    }

    private int ids[] = {R.drawable.publish_icon_job, R.drawable.publish_icon_jianli, R.drawable.publish_icon_piaowu, R.drawable.publish_icon_sale,
            R.drawable.publish_icon_house,
            R.drawable.publish_icon_car, R.drawable.publish_icon_zhuangxiujc, R.drawable.publish_icon_jiaoyu, R.drawable.publish_icon_pets
    };
    private String strs[] = {"招聘信息", "人才浏览", "人资匹配", "求职发布", "招聘发布", "薪酬评价", "绩效评估", "微课程", "基础培训"};

    private void initgridlist() {
        gridlist = new ArrayList<GridItem>();
        for (int i = 0; i < 9; i++) {
            GridItem item = new GridItem();
            item.imgids = ids[i];
            item.text = strs[i];
            gridlist.add(item);
        }

    }

    class pagertrans implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        private View blackgroudview;
        private View centrerview;
        private View topview;

        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);


            } else if (position <= 1) { // (0,1]
                // Fade the page out.

                if (position < 0) {
                    page.setAlpha(1 + position);

                } else {
                    page.setAlpha(1 - position);
                }

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);

            }
        }
    }

    class gridadatper extends BaseAdapter {

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(context, R.layout.index_griditem_layout, null);
           ImageView imageView= (ImageView) view.findViewById(R.id.grid_item_img);
            TextView textView=(TextView)view.findViewById(R.id.grid_item_text);
            imageView.setImageResource(gridlist.get(position).imgids);
            textView.setText(gridlist.get(position).text);
            return view;
        }
    }


    class imgadapter extends FragmentPagerAdapter {

        public imgadapter(FragmentManager fm) {

            super(fm);
            fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
