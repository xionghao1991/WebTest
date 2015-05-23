package kokist.android.webtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * Created by Administrator on 2015/5/23.
 */
public class RegActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MaterialEditText et_phone_number;
    private Button btn_reg_code;
    private AppCompatCheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity_layout);
       toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        et_phone_number=(MaterialEditText)findViewById(R.id.et_phone_number);
        btn_reg_code=(Button) findViewById(R.id.btn_get_regcode);
        btn_reg_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkfrom()) {
                    TelephonyManager phoneMgr = (TelephonyManager) RegActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
                    String myphonenumber = phoneMgr.getLine1Number();
                    Intent intent=new Intent(RegActivity.this,ValidateActivity.class);
                    if (myphonenumber == et_phone_number.getEditableText().toString().trim()) {
                       intent.putExtra("isture",true);
                    }else {
                        intent.putExtra("isture",false);
                    }
                    startActivity(intent);
                }
            }
        });
        checkbox=(AppCompatCheckBox)findViewById(R.id.cb_agree);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegActivity.this.finish();
            }
        });
    }

    private boolean checkfrom() {
        if (!checkbox.isChecked()){
            et_phone_number.setError("请先同意用户协议");
            return false;
        }
        if (et_phone_number.getEditableText().toString().length()!=11) {
            et_phone_number.setError("请输入11位电话号码");
            return false;
        }
        return true;
    }
}
