package kokist.android.webtest;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import kokist.android.webtest.utils.AlertLoading;

/**
 * Created by Administrator on 2015/5/25.
 */
public class EnterPassCodeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    private   String regurl="http://192.168.1.111/Interface/register.ashx";
    private   String loginexpurl="http://192.168.1.111/Participant/Loginexp.aspx?id=";
    private SharedPreferences sp;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shiyan_activity_layout);
        init();
    }

    @SuppressLint("JavascriptInterface")
    private void init() {
        boolean flag = getIntent().getBooleanExtra("flag", false);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (flag){
            toolbar.setTitle("Hr2.0-薪酬评价实验系统");
        }else {
            toolbar.setTitle("Hr2.0-绩效评估实验系统");
        }
        setSupportActionBar(toolbar);
        webView= (WebView) findViewById(R.id.enter_passcode_webview);
        final AlertLoading loading=new AlertLoading(this);
        loading.setText("正在进入实验，请等待...");
        loading.showLoading();
        sp = getSharedPreferences("user", MODE_PRIVATE);
        username=sp.getString("username","");
        regurl=regurl+"?username="+username+"&password=123456";
        RequestQueue mQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(regurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("TAG", response);
                        if (!response.equals("-1")){
                            loginexpurl += response;
                            SharedPreferences.Editor editor= sp.edit();
                            editor.putString("id",response);
                            editor.commit();
                            webView.loadUrl(loginexpurl);
                        }else {
                            String id=sp.getString("id", "");
                            loginexpurl += id;
                            webView.loadUrl(loginexpurl);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                loading.dissmissloading();
                loginexpurl+=102;
                webView.loadUrl(loginexpurl);
            }
        });
        mQueue.add(stringRequest);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                //如果不需要其他对点击链接事件的处理返回true，否则返回false

                return true;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.dissmissloading();
            }
        });


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterPassCodeActivity.this.finish();
            }
        });

    }
    final class InJavaScriptLocalObj {
        public void showSource(String html) {
            System.out.println("====>html="+html);
        }
    }

    @Override
    public void onBackPressed() {
       if (webView.canGoBack()){
           webView.goBack();
       }else {
           EnterPassCodeActivity.this.finish();
       }
    }
}
