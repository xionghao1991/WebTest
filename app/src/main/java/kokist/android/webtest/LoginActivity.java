package kokist.android.webtest;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import kokist.android.webtest.utils.DataBaseUtils;
import kokist.android.webtest.utils.DbHelper;

/**
 * Created by Administrator on 2015/5/22.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private SharedPreferences sp;
    private  DbHelper dbHelper;
    private View btn_lgoin;
    private MaterialEditText et_username;
    private MaterialEditText et_password;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity_layout);
        init();


    }

    private void init() {
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sp= getSharedPreferences("user",MODE_PRIVATE);
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
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
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
        btn_lgoin = findViewById(R.id.btn_login);

       btn_lgoin.setOnClickListener(this);
        et_username=(MaterialEditText) findViewById(R.id.et_username);
        et_password=(MaterialEditText) findViewById(R.id.et_password);
    }

    private void registerUser(String country, String phone) {

        ContentValues cv=new ContentValues();
        cv.put("username", phone);
        cv.put("password","123456");
        cv.put("phonenumber", phone);
        DataBaseUtils utils=new DataBaseUtils(this);
        long result=  utils.InsertValue(cv,"user");

        if (result>0){
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor= sp.edit();
            editor.putBoolean("islogin",true);
            editor.putString("username",phone);
            editor.commit();
            Intent intent=new Intent(this,UpadateUserInfoActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
               if (checkForm()){
                   username=  et_username.getEditableText().toString();
                   password= et_password.getEditableText().toString();
                   DataBaseUtils utils=new DataBaseUtils(this);
                  Cursor cursor= utils.Query("select * from user where username=? and password= ?", new String[]{username, password});
                   Cursor cursor1=utils.Query("select *from user where phonenumber=? and password=?",new String[]{username,password});
                   if (cursor.getCount()>0||cursor1.getCount()>0){
                       SharedPreferences.Editor editor= sp.edit();
                       editor.putBoolean("islogin",true);
                       editor.putString("username", username);
                       editor.commit();
                       this.finish();
                   }
                   cursor.close();
                   cursor1.close();
               }
                break;
        }
    }

    private boolean checkForm() {
        if (et_username.getEditableText().toString().isEmpty()){
            et_username.setError("用户名不能为空");
            return  false;
        }
        if (et_password.getEditableText().toString().isEmpty()){
            et_password.setError("密码不能为空");
            return  false;
        }
        return true;
    }
}
