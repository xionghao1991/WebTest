package kokist.android.webtest;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import kokist.android.webtest.utils.DataBaseUtils;
import kokist.android.webtest.utils.DbHelper;

/**
 * Created by Administrator on 2015/5/22.
 */
public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SharedPreferences sp;
    private  DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity_layout);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.reg_by_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
//                startActivity(intent);
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");

// 提交用户信息
                            registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(LoginActivity.this);

            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
    }

    private void registerUser(String country, String phone) {

        ContentValues cv=new ContentValues();
        cv.put("username",phone);
        cv.put("password","123456");
        cv.put("phonenumber",country+phone);
        DataBaseUtils utils=new DataBaseUtils(this);
        long result=  utils.InsertValue(cv,"user");

        if (result>0){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
           sp= getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            editor.putBoolean("islogin",true);
            editor.putString("username",phone);
            editor.commit();
            Intent intent=new Intent(this,UpadateUserInfoActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
