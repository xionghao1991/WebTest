package kokist.android.webtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/5/24.
 */
public class PeixunActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lession_list;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peixunlist_activity_layout);
        init();

    }

    private void init() {
       flag= getIntent().getBooleanExtra("flag",false);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeixunActivity.this.finish();
            }
        });
        lession_list=  (ListView)findViewById(R.id.peixunlists);
        lession_list.setAdapter(new listadapter());
        lession_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

                convertView=View.inflate(PeixunActivity.this,R.layout.peixun_list_layout,null);

            }
            return convertView;
        }
    }
}
