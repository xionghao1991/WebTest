package kokist.android.webtest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import kokist.android.webtest.utils.PopListAdapter;

/**
 * Created by Administrator on 2015/5/22.
 */
public class JobActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ListView listview;
    boolean xinchou=false;
    private int flag;
    private View xinhoujjili_rl;
    private PopupWindow popupWindow;
    private LayoutInflater inflater;
    private ListView leftmenu;
    private ListView rightmenu;
    private PopListAdapter leftadapter;
    private PopListAdapter rightadapter;
    private View spiterbar;
    private TextView xinchoujili_rl_text;
    private ImageView xinchoujili_rl_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_layout);
        flag = getIntent().getIntExtra("flag", 0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (flag == 0) {
            toolbar.setTitle("HR2.0-招聘信息");
        } else {
            toolbar.setTitle("HR2.0-人才浏览");
        }
        listview = (ListView) findViewById(R.id.joblist);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);
        inflater=this.getLayoutInflater();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobActivity.this.finish();
            }
        });
        listview.setAdapter(new listadaper());
        xinhoujjili_rl=findViewById(R.id.xinchoujili_rl);
        xinchoujili_rl_text= (TextView) findViewById(R.id.xinchoujili_rl_text);
        xinchoujili_rl_img=(ImageView)findViewById(R.id.xinchoujili_rl_img);
        xinhoujjili_rl.setOnClickListener(this);
        spiterbar= findViewById(R.id.spiter_bar);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.xinchoujili_rl:
               if (!xinchou){
                   showpopwin();
                   xinchoujili_rl_text.setTextColor(getResources().getColor(R.color.orgen));
                   xinchoujili_rl_img.setImageResource(R.drawable.tradeline_filter_btn_icon_press);
                   xinchou=true;
               }else {
                   popupWindow.dismiss();

               }
                break;
        }
    }

    private void showpopwin() {
        String [] strleft={"货币偏好","娱乐偏好"};
        final String [] strright1={"人民币","美元","欧元","日元"};
        final String[] strright2={"休假多","娱乐互动多"};
        popupWindow=new PopupWindow(this);
        popupWindow.setHeight(-2);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popwinbg));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(listview.getWidth());
        View contentview=inflater.inflate(R.layout.xinchou_popwin_layout,null);
        leftmenu= (ListView)contentview.findViewById(R.id.left_popwin_menu);
        rightmenu= (ListView)contentview.findViewById(R.id.right_popwin_menu);
        leftmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i!=position) {
                        parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.whlite));
                    }else {
                        parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.lightgary));
                    }
                }
                leftadapter.setChecked_postion(position);
                if (position==0){
                    rightadapter.setStrs(strright1);
                    rightadapter.setChecked_postion(0);
                    rightadapter.notifyDataSetChanged();
                }else {
                    rightadapter.setStrs(strright2);
                    rightadapter.setChecked_postion(0);
                    rightadapter.notifyDataSetChanged();
                }
            }
        });
        rightmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i!=position) {
                       TextView textView= (TextView) parent.getChildAt(i);
                        textView.setTextColor(getResources().getColor(R.color.text_normal_gray));
                    }else {
                        TextView textView= (TextView) parent.getChildAt(i);
                        textView.setTextColor(getResources().getColor(R.color.orgen));
                    }
                }
                rightadapter.setChecked_postion(position);
            }
        });
        leftadapter=new PopListAdapter(this,strleft,false);
        rightadapter=new PopListAdapter(this,strright1,true);
        leftmenu.setAdapter(leftadapter);
        rightmenu.setAdapter(rightadapter);
        popupWindow.setContentView(contentview);
        popupWindow.showAsDropDown(spiterbar);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                xinchou=false;
                xinchoujili_rl_text.setTextColor(getResources().getColor(R.color.text_normal_gray));
                xinchoujili_rl_img.setImageResource(R.drawable.tradeline_filter_btn_icon_normal);
            }
        });
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
    }

    @Override
    protected void onDestroy() {
        if (popupWindow!=null) {
            popupWindow.dismiss();
        }
        super.onDestroy();
    }

    class listadaper extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                int layoutid = 0;
                if (flag == 0) {
                    layoutid = R.layout.news_list_item;
                } else if (flag == 1) {
                    layoutid = R.layout.jianli_layout;
                }
                convertView = View.inflate(JobActivity.this, layoutid, null);
            }
            return convertView;
        }

        class Viewholder {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
