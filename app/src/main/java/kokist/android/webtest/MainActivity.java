package kokist.android.webtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cn.smssdk.SMSSDK;
import kokist.android.webtest.fragment.BaseFragment;
import kokist.android.webtest.fragment.LeftMenuFragment;
import kokist.android.webtest.utils.DbHelper;
import kokist.android.webtest.utils.Utils;


public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout rootview;
    private ActionBarDrawerToggle mDrawerToggle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SMSSDK.initSDK(this, "7a79425cb0e0", "b9a0b736217d7135bae37fce384004e7");
        BaseFragment fragment=new BaseFragment();
        LeftMenuFragment  menuFragment=new LeftMenuFragment();
        initdatabase();

        getSupportFragmentManager().beginTransaction().replace(R.id.content,fragment,"index").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.left_menu, menuFragment, "menu").commit();
    }

    private void initdatabase() {
        DbHelper dbHelper=new DbHelper(this,"userdata",1);

    }

    private void initView() {
        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootview = (DrawerLayout)findViewById(R.id.root_drawerview);
        mDrawerToggle = new ActionBarDrawerToggle(this, rootview, toolbar, 0,
              0){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                dosomething(intent);
            }
        };
        mDrawerToggle.syncState();
        rootview.setDrawerListener(mDrawerToggle);
        toolbar.setY(-Utils.dpToPx(56));
        toolbar.animate().translationY(0).setDuration(400).setStartDelay(300).start();

    }

    private void dosomething(Intent intent) {

        if (intent==null){
            return;
        }else {
            startActivity(intent);
        }
    }

    public  void closedarwer(){
        rootview.closeDrawer(Gravity.LEFT);
    }
    public void closedarwerAndjump(Intent intent){
        this.intent=intent;
        rootview.closeDrawer(Gravity.LEFT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        intent=null;
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
