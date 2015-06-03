package kokist.android.webtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/5/22.
 */
public class JobActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listview;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_layout);
       flag= getIntent().getIntExtra("flag",0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (flag==0){
            toolbar.setTitle("HR2.0-招聘信息");
        }else {
            toolbar.setTitle("HR2.0-人才浏览");
        }
         listview= (ListView) findViewById(R.id.joblist);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobActivity.this.finish();
            }
        });
        listview.setAdapter(new listadaper());

    }
    class listadaper extends BaseAdapter{

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
               int layoutid=0;
               if (flag==0) {
                   layoutid=R.layout.news_list_item;
               }else if(flag==1){
                  layoutid=R.layout.jianli_layout;
               }
               convertView = View.inflate(JobActivity.this, layoutid, null);
           }
            return  convertView;
        }
        class  Viewholder{

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
