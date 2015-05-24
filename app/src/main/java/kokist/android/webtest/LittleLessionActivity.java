package kokist.android.webtest;

import android.content.Intent;
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
public class LittleLessionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lession_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligt_lession_layout);
        init();

    }

    private void init() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
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
                startActivity(intent);
            }
        });

    }
    class  listadapter extends BaseAdapter
    {

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
            }
            return convertView;
        }
    }
}
