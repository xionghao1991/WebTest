package kokist.android.webtest.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/26.
 */
public class AlertLoading {
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
        dialog=new AlertDialog.Builder(context,R.style.Dialog).create();
        dialog.show();
       dialog.setContentView(view);
        imageView.setImageResource(R.drawable.loading_yuan);

        ObjectAnimator  anmi1=ObjectAnimator.ofFloat(imageView, "translationY", -120, 0);
        anmi1.setDuration(1000);
        anmi1.setRepeatCount(30);
        anmi1.setRepeatMode(ValueAnimator.REVERSE);
        anmi1.start();
    }
    public void setText(String  text){
         tv_loading.setText(text);
    }

    public  void dissmissloading(){
        dialog.dismiss();
    }
    public int getRanint(){
        Random random=new Random();
       return random.nextInt(3);
    }

}
