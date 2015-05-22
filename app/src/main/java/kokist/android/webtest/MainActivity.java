package kokist.android.webtest;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import kokist.android.webtest.fragment.BaseFragment;
import kokist.android.webtest.utils.Utils;


public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout rootview;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        BaseFragment fragment=new BaseFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment,"index").commit();
    }

    private void initView() {
        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootview = (DrawerLayout)findViewById(R.id.root_drawerview);
        mDrawerToggle = new ActionBarDrawerToggle(this, rootview, toolbar, 0,
              0);
        mDrawerToggle.syncState();
        rootview.setDrawerListener(mDrawerToggle);
        toolbar.setY(-Utils.dpToPx(56));
        toolbar.animate().translationY(0).setDuration(400).setStartDelay(300).start();

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
