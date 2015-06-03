package kokist.android.webtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/24.
 */
public class LittleLessionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lession_list;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligt_lession_layout);
        init();

    }

    private void init() {
       flag= getIntent().getBooleanExtra("flag",false);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        if (flag){
            toolbar.setTitle("HR2.0-人职匹配");
        }
        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LittleLessionActivity.this.finish();
            }
        });
        lession_list=  (ListView)findViewById(R.id.lession_list);
        lession_list.setAdapter(new listadapter());
        lession_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LittleLessionActivity.this,LittleLessionDetailActivity.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });

    }
    class  listadapter extends BaseAdapter
    {

        private int layoutid;

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

            if (convertView==null){

                convertView=View.inflate(LittleLessionActivity.this,R.layout.lession_list_item_layout,null);
                if (flag) {
                   ImageView iv= (ImageView) convertView.findViewById(R.id.list_item_pic);
                    iv.setImageResource(R.drawable.test_post);
                    TextView tv = (TextView) convertView.findViewById(R.id.tv_lessison_title);
                    tv.setText("员工自我评估的目的，在于得到员工对他们的体现的自我见解。这是一个在整个绩效考的进程中给予员工发言机遇的强有力的要领。");
                }
            }
            return convertView;
        }
    }
}
