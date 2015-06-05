package kokist.android.webtest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import kokist.android.webtest.utils.AlertLoading;
import kokist.android.webtest.utils.LoadingView;

/**
 * Created by Administrator on 2015/5/24.
 */
public class LittleLessionDetailActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private WebView little_lession_webview;
    private FrameLayout framelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.little_lession_detail_layout);
        init();
    }

    private void init() {
        boolean flag = getIntent().getBooleanExtra("flag", false);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (flag){
            toolbar.setTitle("人职匹配调研");
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LittleLessionDetailActivity.this.finish();
            }
        });
        framelayout= (FrameLayout)findViewById(R.id.framelayout);
        little_lession_webview=(WebView)findViewById(R.id.little_lession_webview);
        little_lession_webview.setBackgroundResource(R.color.whlite);
        little_lession_webview.getSettings().setJavaScriptEnabled(true);
        if (flag){
            little_lession_webview.loadUrl("http://www.wenjuan.com/s/nYvayy/");
        }else {
            little_lession_webview.loadUrl("file:///android_asset/index.html");
        }
        final LoadingView alertLoading=new LoadingView();
        little_lession_webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                alertLoading.showloadingView(framelayout,LittleLessionDetailActivity.this);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
               alertLoading.dissMissloadingView();
            }
        });
    }
}
