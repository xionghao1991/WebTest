package kokist.android.webtest.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/21.
 */
public class BaseFragment extends Fragment {

    private View view;
    private ViewPager viewpager;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.index, null);
        initView();
        return view;
    }
    private List<Fragment> list;
    private void initView() {
        list=new ArrayList<Fragment>();
       viewpager=(ViewPager) view.findViewById(R.id.headerviewpager);
        list.add(new ImageFragment());
        list.add(new  ImageFragment());
        list.add(new ImageFragment());
        viewpager.setPageTransformer(true, new pagertrans());
        viewpager.setAdapter(new imgadapter(getFragmentManager()));

    }
   class  pagertrans implements ViewPager.PageTransformer {
       private static final float MIN_SCALE = 0.75f;
       private View blackgroudview;
       private View centrerview;
       private View topview;

       @Override
       public void transformPage(View page, float position) {
           int pageWidth = page.getWidth();
           blackgroudview= page.findViewById(R.id.blackgroudimg);
           centrerview=page.findViewById(R.id.centerimg);
           topview=page.findViewById(R.id.topimg);
           if (position < -1) { // [-Infinity,-1)
               // This page is way off-screen to the left.
               page.setAlpha(0);


           }  else if (position <= 1) { // (0,1]
               // Fade the page out.
             if (position<0){
                 page.setAlpha(1+position);
                 page.setTranslationX(0);
             }else {
                 page.setAlpha(1-position);
                 page.setTranslationX(0);
             }

           } else { // (1,+Infinity]
               // This page is way off-screen to the right.
               page.setAlpha(0);
           }
       }
   }




    class  imgadapter extends FragmentPagerAdapter{

        public imgadapter(FragmentManager fm) {
            super(fm);
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
