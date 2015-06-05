package kokist.android.webtest.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/26.
 */
public class AlertLoading {
    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int WHITE = 0xffffffff;
    private static final int GREEN = 0xff80ff80;

    private final FrameLayout view;
    private  Context context;
    private AppCompatDialog dialog;
    private LayoutInflater inflater;

    private TextView tv_loading;
    private Splash splash;

    public  AlertLoading(Context context){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
         view= (FrameLayout) inflater.inflate(R.layout.loading_layout, null);

    }
    public void  showLoading(){
        dialog=new AlertDialog.Builder(context, R.style.Dialog).create();
        dialog.show();
        dialog.setContentView(view);
        splash=new Splash(context);
        view.addView(splash);


    }


    public  void dissmissloading(){
        splash.splashAndDisMiss();
    }


}
