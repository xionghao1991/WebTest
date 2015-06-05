package kokist.android.webtest.utils;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by xiong on 2015/6/4.
 */
public class LoadingView {

    private Splash splash;
    private FrameLayout rootview;
    public void showloadingView(final FrameLayout rootview,Context context){
        if (rootview!=null){
            splash=new Splash(context);
            rootview.addView(splash);
            splash.setOnAnimatorLister(new Splash.onAnimatiorLister() {
                @Override
                public void onAnimatorEnd(Splash splash) {
                    rootview.removeView(splash);
                }
            });

        }
    }
    public void dissMissloadingView(){


        splash.splashAndDisMiss();

    }
}
