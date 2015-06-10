package kokist.android.webtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AppCompatPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import kokist.android.webtest.utils.ListAdapter;

/**
 * Created by xiong on 2015/6/9.
 */
public class PiPeiActivity extends AppCompatActivity {
    private ListView listview;
    private Toolbar toolbar;
    private ListAdapter adapter;
    private View rootview;
    private View popview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pipeilayout);
        init();
    }

    private void init() {
        rootview=findViewById(R.id.root_view);
        listview = (ListView) findViewById(R.id.pipei_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PiPeiActivity.this.finish();
            }
        });
        initlistView();

    }

    private static final String[] strs = {"测试技术", "岗位特征", "组织行为", "匹配咨询", "案例指引"};

    private static int[] listdrableids = {R.drawable.ic_item_beat, R.drawable.ic_item_daoshu, R.drawable.ic_item_game, R.drawable.ic_item_notif, R.drawable.ic_item_quanqiu};

    private void initlistView() {
        adapter = new ListAdapter(this,strs,listdrableids);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int[] localtion = new int[2];
                view.getLocationOnScreen(localtion);
                int x=localtion[0];
                int y=localtion[1];
                Intent intent=new Intent();
                intent.putExtra("localtion",localtion);
                switch (position) {
                    case 0:
                        intent.setClass(PiPeiActivity.this,PipeiDetailActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        AlertDialog dialog=new AlertDialog.Builder(PiPeiActivity.this).create();
                        dialog.show();
                        dialog.setTitle("组织行为");
                        dialog.setContentView(R.layout.zhuzhi_layout);
                        dialog.findViewById(R.id.shangsheng).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        dialog.findViewById(R.id.xiajiang).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        dialog.findViewById(R.id.pingjun).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        dialog.show();


                        break;
                }

            }
        });
    }

}
