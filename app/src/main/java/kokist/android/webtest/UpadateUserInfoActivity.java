package kokist.android.webtest;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.rengwuxian.materialedittext.MaterialEditText;

import kokist.android.webtest.utils.DataBaseUtils;

/**
 * Created by Administrator on 2015/5/24.
 */
public class UpadateUserInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialEditText et_username;
    private MaterialEditText et_password;
    private String username;
    private String password;
    private Button btn_update;
    private RadioGroup radio;
    private String destext;
    private SharedPreferences sp;
    private final static String Tag="UpadateUserInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_info_layout);
       toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        sp= getSharedPreferences("user",MODE_PRIVATE);
        init();


    }

    private void init() {
        et_username= (MaterialEditText) findViewById(R.id.et_username);
        et_password=(MaterialEditText)findViewById(R.id.et_password);
        final DataBaseUtils utils=new DataBaseUtils(this);
        TelephonyManager phoneMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        final String myphonenumber = phoneMgr.getLine1Number();
        Cursor cursor= utils.Query("select * from user where phonenumber=?", new String[]{myphonenumber});
        while (cursor.moveToNext()){
            username= cursor.getString(cursor.getColumnIndex("username"));
            password=cursor.getString(cursor.getColumnIndex("password"));
            Log.d(Tag,"username:--"+username+"--password:--"+password);
        }
        et_password.setText(password);
        et_username.setText(username);
        radio= (RadioGroup)findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                  switch (checkedId){
                      case R.id.company_user_rab:
                          destext="公司用户";
                          break;
                      case R.id.person_user_rab:
                          destext="个人用户";
                          break;
                  }
            }
        });
        btn_update= (Button)findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    ContentValues contentValues=new ContentValues();
                    contentValues.put("username",username);
                    contentValues.put("password",password);
                    contentValues.put("desc",destext);
                    int result=utils.Update(contentValues,"user","phonenumebr=?",new String[]{myphonenumber});
                    if (result>0){
                     SharedPreferences.Editor editor= sp.edit();
                        editor.putString("username",username);
                        editor.putString("des",destext);
                        editor.commit();
                        UpadateUserInfoActivity.this.finish();
                    }
                }
            }
        });

    }

    private boolean checkForm() {
        if(et_username.getEditableText().toString().isEmpty()){
            et_username.setError("用户名不能为空");
            return  false;
        }
        if (et_password.getEditableText().toString().isEmpty()){
            et_password.setError("密码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(destext)){
            destext="No";
        }
        return true;

    }
}

