package kokist.android.webtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import kokist.android.webtest.utils.ListAdapter;

/**
 * Created by xiong on 2015/6/10.
 */
public class ZhaoPinActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listview;
    private Toolbar toolbar;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhaoping_activity_layout);
        init();
    }

    private void init() {
        listview = (ListView) findViewById(R.id.zhaopin_listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhaoPinActivity.this.finish();
            }
        });
        initlistview();
    }

    private int[] drableids = {R.drawable.publish_icon_jianli2, R.drawable.publish_icon_home_sale_rss, R.drawable.publish_icon_ticket_sale, R.drawable.publish_icon_full_time_job_rss, R.drawable.publish_icon_second_hand_goods_rss,
            R.drawable.publish_ic_lifestyle, R.drawable.publish_icon_pet_sale
    };
    private String[] strs = {"就业形势", "政策利导", "招聘信息", "信息发布", "人才浏览", "竞聘互动", "案例指引"};

    private void initlistview() {
        adapter = new ListAdapter(this, strs, drableids);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        intent.setClass(ZhaoPinActivity.this, JobActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        initchoseView(intent);
                        break;
                    case 4:
                        intent.setClass(ZhaoPinActivity.this, JobActivity.class);
                        intent.putExtra("flag",1);
                        startActivity(intent);
                        break;
                    case 5:
                        break;
                }
            }
        });
    }

    private void initchoseView(Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog2);
        View view = View.inflate(this, R.layout.xinchoupianhao_layout, null);
        TextView fisttv = (TextView) view.findViewById(R.id.fist);
        TextView secondtv = (TextView) view.findViewById(R.id.second);
        fisttv.setText("职位发布");
        secondtv.setText("求职发布");
        fisttv.setOnClickListener(this);
        secondtv.setOnClickListener(this);
        builder.setView(view).setNegativeButton("取消", null).show();
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.fist:
                intent = new Intent(this, Publish_Company_Activity.class);
                startActivity(intent);
                break;
            case R.id.second:
                intent = new Intent(this, PublishResumeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
