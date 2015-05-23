package kokist.android.webtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/5/23.
 */
public class ValidateActivity extends AppCompatActivity {

    private Button btn_respost;
    int count=60;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                count--;
                if (count!=0) {
                    btn_respost.setText(count + "秒后重新发送");
                }else {
                    btn_respost.setText("重新发送验证码");
                    btn_respost.setEnabled(true);
                    timer.cancel();
                }
            }
            super.handleMessage(msg);
        }
    };
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Message message=Message.obtain();
            message.what=1;
           handler.sendMessage(message);
        }
    };
    private Timer timer=new Timer();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate_activity_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateActivity.this.finish();
            }
        });
        btn_respost=(Button)findViewById(R.id.btn_respost);
        timer.schedule(timerTask,1000,1000);
    }

}
