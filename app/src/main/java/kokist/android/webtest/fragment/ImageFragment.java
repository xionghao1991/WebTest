package kokist.android.webtest.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/21.
 */
@SuppressLint("ValidFragment")
public class ImageFragment extends Fragment {

    private  int i;
    private View topimg;
    private View centerimg;
    private View blackgroudimg;


    public ImageFragment(int i) {
        this.i=i;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView view = (ImageView) inflater.inflate(R.layout.imglayout,null);

       switch (i){
           case 1:
             view.setImageResource(R.drawable.img1);

               break;
           case 2:
               view.setImageResource(R.drawable.img2);

               break;
           case 3:
               view.setImageResource(R.drawable.img3);
               break;
       }
        return  view;
    }
}
