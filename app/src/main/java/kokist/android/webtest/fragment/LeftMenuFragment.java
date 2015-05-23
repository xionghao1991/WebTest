package kokist.android.webtest.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kokist.android.webtest.LoginActivity;
import kokist.android.webtest.MainActivity;
import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/23.
 */
public class LeftMenuFragment extends Fragment {

    private  TextView textView;
    private Context context;
    private SharedPreferences sp;
    private boolean islogin;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=   getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view= inflater.inflate(R.layout.left_menu,null);
       textView= (TextView) view.findViewById(R.id.username_text);
       sp= context.getSharedPreferences("user", Context.MODE_PRIVATE);
       islogin= sp.getBoolean("islogn",false);
        if (islogin){
            textView.setText("username");
        }else {
            textView.setText("点击登录");

        }
        textView.setClickable(!islogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LoginActivity.class);
                startAct(intent);

            }
        });

        return view;
    }

    private void startAct(Intent intent) {
        ((MainActivity) context).closedarwerAndjump(intent);
    }

}
