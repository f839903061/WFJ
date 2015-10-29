package hy.cz.wfj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private ImageButton avatarImage;
    private ImageButton settingsImage;
    private ImageButton messageImage;

    private TextView concern_goods_tv;
    private TextView concern_shop_tv;
    private TextView concern_browser_tv;
    private static final String TAG="fengluchun";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用fresco 组件必要的初始化工作
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.main_layout);

        //
        initializeComponent();
        initializeListener();

    }

    /**
     * 初始化组件
     */
    private void initializeComponent() {
        avatarImage=(ImageButton)findViewById(R.id.personal_unlogin_avatar);
        settingsImage=(ImageButton)findViewById(R.id.personal_setting);
        messageImage=(ImageButton)findViewById(R.id.personal_common_message);

        concern_goods_tv=(TextView)findViewById(R.id.personal_goods_list_title);
        concern_shop_tv=(TextView)findViewById(R.id.personal_shop_list_title);
        concern_browser_tv=(TextView)findViewById(R.id.personal_browsing_list_title);
    }

    /**
     * 初始化所以监听器
     */
    private void initializeListener() {
        avatarImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
        messageImage.setOnClickListener(this);

        concern_goods_tv.setOnClickListener(this);
        concern_shop_tv.setOnClickListener(this);
        concern_browser_tv.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 响应点击view视图事件
     * @param v
     *          点击的view视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //title incident
            case R.id.personal_unlogin_avatar:
                myToast("login");
                break;
            case R.id.personal_setting:
                myToast("settings");
                break;
            case R.id.personal_common_message:
                myToast("message");
                break;
            //concern incident
            case R.id.personal_goods_list_title:
                myToast("goods");
                break;
            case R.id.personal_shop_list_title:
                myToast("shop");
                break;
            case R.id.personal_browsing_list_title:
                myToast("browser");
            default:
                break;
        }
    }

    /**
     * 提示工具
     * @param pText
     *              要显示的内容
     */
    private void myToast(String pText){
        Toast.makeText(getApplicationContext(),"--->"+pText+"<---",Toast.LENGTH_SHORT).show();
    }
}
