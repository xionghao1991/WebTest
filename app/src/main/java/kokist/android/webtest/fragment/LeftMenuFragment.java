package kokist.android.webtest.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import kokist.android.webtest.LoginActivity;
import kokist.android.webtest.LoginoutActivity;
import kokist.android.webtest.MainActivity;
import kokist.android.webtest.R;

/**
 * Created by Administrator on 2015/5/23.
 */
public class LeftMenuFragment extends Fragment {

    private TextView textView;
    private Context context;
    private SharedPreferences sp;
    private boolean islogin;
    private String username;
    private ImageView imgview;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        checklogin();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_menu, null);
        textView = (TextView) view.findViewById(R.id.username_text);
        View rl_jumto_userinfoview = view.findViewById(R.id.rl_jumpto_userinfoupdate);
        View tv_exit=view.findViewById(R.id.tv_exit);
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).closedarwer();
                ((MainActivity)context).onBackPressed();
            }
        });
        rl_jumto_userinfoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (islogin) {
                    intent = new Intent(context, LoginoutActivity.class);
                    startAct(intent);
                }else {
                     intent=new Intent(context,LoginActivity.class);
                     startAct(intent);
                }
            }
        });
        sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        imgview=(ImageView)view.findViewById(R.id.person_user_icon);
        Picasso.with(context).load(R.mipmap.ic_launcher).transform(new CropSquareTransformation()).into(imgview);
        checklogin();
        textView.setClickable(!islogin);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!islogin) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startAct(intent);
                }
            }
        });

        return view;
    }
    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }
        @Override public String key() { return "square()"; }
    }

    private void checklogin() {
        islogin = sp.getBoolean("islogin", false);
        if (islogin) {
            username = sp.getString("username", "");
            textView.setText(username);
        } else {
            textView.setText("点击登录");

        }
    }


    private void startAct(Intent intent) {
        ((MainActivity) context).closedarwerAndjump(intent);
    }

}
