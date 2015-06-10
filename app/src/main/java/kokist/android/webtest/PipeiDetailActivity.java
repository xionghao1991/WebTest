package kokist.android.webtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by xiong on 2015/6/9.
 */
public class PipeiDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pipeidetail_layout);
        init();
    }

    private void init() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PipeiDetailActivity.this.finish();
            }
        });
        findViewById(R.id.DISK).setOnClickListener(this);
        findViewById(R.id.jixing).setOnClickListener(this);
        findViewById(R.id.MBTF).setOnClickListener(this);
        findViewById(R.id.huolande).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent();
        intent.setClass(this,LittleLessionDetailActivity.class);
        intent.putExtra("flag",true);
        switch (v.getId()){
            case R.id.DISK:
                intent.putExtra("title","DISK测试");
                intent.putExtra("id", R.id.DISK);
                break;
            case R.id.jixing:
                intent.putExtra("title","几型人格测试");
                intent.putExtra("id", R.id.jixing);
                break;
            case R.id.MBTF:
                intent.putExtra("title","MBTF测试");
                intent.putExtra("id", R.id.MBTF);
                break;
            case R.id.huolande:
                intent.putExtra("title","霍兰德性格测试");
                intent.putExtra("id", R.id.huolande);
                break;
        }
        startActivity(intent);
    }
}
