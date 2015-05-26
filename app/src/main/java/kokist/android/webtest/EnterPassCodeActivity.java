package kokist.android.webtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kokist.android.webtest.utils.AlertLoading;

/**
 * Created by Administrator on 2015/5/25.
 */
public class EnterPassCodeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private View loadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiyan_activity_layout);
        init();
    }

    private void init() {
        boolean flag = getIntent().getBooleanExtra("flag", false);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (flag){
            toolbar.setTitle("薪酬评价实验系统");
        }else {
            toolbar.setTitle("绩效评估实验系统");
        }
        setSupportActionBar(toolbar);
       loadingview= findViewById(R.id.loadingview);
        final AlertLoading loading=new AlertLoading(this);
        loading.setText("正在进入实验，请等待...");
        loading.showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.dissmissloading();
                loadingview.setVisibility(View.GONE);
            }
        }, 3000);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterPassCodeActivity.this.finish();
            }
        });
    }
}
