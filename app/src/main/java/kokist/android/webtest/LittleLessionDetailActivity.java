package kokist.android.webtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by Administrator on 2015/5/24.
 */
public class LittleLessionDetailActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private WebView little_lession_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.little_lession_detail_layout);
        init();
    }

    private void init() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LittleLessionDetailActivity.this.finish();
            }
        });
        little_lession_webview=(WebView)findViewById(R.id.little_lession_webview);

    }
}
