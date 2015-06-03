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
    private final ImageView imageView;
    private final View view;
    private  Context context;
    private AppCompatDialog dialog;
    private LayoutInflater inflater;
    private int[] ids={R.drawable.loading_yuan,R.drawable.loading_fangxing,R.drawable.loading_sanjiao};
    private TextView tv_loading;

    public  AlertLoading(Context context){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.loading_layout, null);
         imageView=(ImageView) view.findViewById(R.id.iv_loading);
        tv_loading= (TextView)view.findViewById(R.id.tv_loading);
    }
    public void  showLoading(){
        dialog=new AlertDialog.Builder(context, R.style.Dialog).create();
        dialog.show();
       dialog.setContentView(view);
        final GradientDrawable drawable=new GradientDrawable();

        drawable.setColor(RED);

        imageView.setImageDrawable(drawable);
        ObjectAnimator ofInt=ObjectAnimator.ofInt(drawable, "Color",WHITE,RED,BLUE,GREEN,WHITE);
        ofInt.setEvaluator(new ArgbEvaluator());
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.setDuration(5000);
        ofInt.setRepeatCount(ValueAnimator.INFINITE);
         ofInt.setRepeatMode(ValueAnimator.REVERSE);
        ofInt.start();

        ObjectAnimator  anmi1=ObjectAnimator.ofFloat(imageView, "translationY", -120, 0);
        anmi1.setDuration(1000);
        anmi1.setInterpolator(new BounceInterpolator());
        anmi1.setRepeatCount(ValueAnimator.INFINITE);
        anmi1.setRepeatMode(ValueAnimator.REVERSE);
        anmi1.start();

    }
    public void setText(String  text){
         tv_loading.setText(text);
    }

    public  void dissmissloading(){
//        dialog.dismiss();
    }
    public int getRanint(){
        Random random=new Random();
       return random.nextInt(3);
    }

}
