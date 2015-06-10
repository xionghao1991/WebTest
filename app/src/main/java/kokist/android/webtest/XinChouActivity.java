package kokist.android.webtest;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import kokist.android.webtest.utils.ListAdapter;

/**
 * Created by xiong on 2015/6/10.
 */
public class XinChouActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

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
                XinChouActivity.this.finish();
            }
        });
        initlistview();
    }
    private int[] drableids = {R.drawable.personal_centre_jobs_default, R.drawable.personal_centre_my_popularize,
            R.drawable.personal_centre_post_default, R.drawable.personal_centre_setting_default
    };
    private String[] strs = {"期望薪资", "满意度偏好", "公平性偏好", "薪酬筛选设置"};
    private void initlistview() {
     adapter=new ListAdapter(this,strs,drableids);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.Dialog2);
        builder.setTitle(strs[position]);


        switch (position){

            case 0:
            builder.setSingleChoiceItems(new String[]{"2000-3000", "3000-5000", "5000-8000", "8000-10000", "其他"}, 1, null).setPositiveButton("确定", null)
            .setNegativeButton("取消", null).show();
                break;
            case 1:
                builder.setSingleChoiceItems(new String[]{"一般", "比较满意", "非常满意", "不满意"}, 1, null).setPositiveButton("确定", null)
                        .setNegativeButton("取消", null).show();
                break;
            case 2:
                builder.setSingleChoiceItems(new String[]{"一般", "普通", "非常好", "差"}, 1, null).setPositiveButton("确定", null)
                        .setNegativeButton("取消", null).show();
                break;
            case 3:
               View bulderview=View.inflate(this,R.layout.xinchoupianhao_layout,null);
                bulderview.findViewById(R.id.fist).setOnClickListener(this);
                bulderview.findViewById(R.id.second).setOnClickListener(this);
                builder.setView(bulderview,16,16,16,16);
                builder.setNegativeButton("取消",null).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fist:
                break;
            case R.id.second:
                break;
        }
    }
}
